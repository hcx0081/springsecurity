package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * {@code @Description:}
 */
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();// 这里先关闭，否则访问不到资源
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().loginPage("/login").loginProcessingUrl("/doLogin")
            .successHandler(new MyAuthenticationSuccessHandler()).permitAll()
            .failureHandler(new MyAuthenticationFailureHandler()).permitAll();
        http.logout().logoutUrl("/doLogout")
            .logoutSuccessHandler(new MyLogoutSuccessHandler());
        return http.build();
    }
}