package com.springsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code @Description:}
 */
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();// 这里先关闭，否则访问不到资源
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin();
        http.sessionManagement().maximumSessions(1)
            // .expiredUrl("/login");
            .expiredSessionStrategy(event -> {
                HttpServletResponse response = event.getResponse();
                response.setContentType("application/json;charset=utf-8");
                Map<String, Object> map = new HashMap<>();
                map.put("code", 500);
                map.put("msg", "当前会话已经失效，请重新登录");
                String json = new ObjectMapper().writeValueAsString(map);
                response.getWriter().write(json);
            });
        return http.build();
    }
}