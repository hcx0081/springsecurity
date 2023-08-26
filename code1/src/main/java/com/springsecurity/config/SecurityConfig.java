package com.springsecurity.config;

import com.springsecurity.provider.KaptchaAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * {@code @Description:}
 */
@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                               .username("admin")
                               .password("admin")
                               .roles("ADMIN")
                               .build();
        return new InMemoryUserDetailsManager(user);
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        KaptchaAuthenticationProvider provider = new KaptchaAuthenticationProvider();
        // 必须设置UserDetailsService，因为容器中如果存在AuthenticationProvider，那么全局UserDetailsService就不进行注册
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();// 这里先关闭，否则访问不到资源
        http.authorizeRequests()
            .antMatchers("/kaptcha/**").permitAll()
            .anyRequest().authenticated();
        http.formLogin().loginPage("/login").loginProcessingUrl("/doLogin").permitAll();
        return http.build();
    }
}