package com.springsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurity.filter.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.annotation.Resource;

/**
 * {@code @Description:}
 */
@Configuration
public class SecurityConfig {
    @Resource
    private AuthenticationConfiguration authenticationConfiguration;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        // 必须配置全局AuthenticationManager
        loginFilter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
        // 认证成功
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(authentication);
            response.getWriter().write(json);
        });
        // 认证失败
        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setContentType("application/json;charset=utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(exception);
            response.getWriter().write(json);
        });
        http.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class);
        
        http.authorizeRequests().anyRequest().authenticated();
        // http.csrf().disable();
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        return http.build();
    }
}