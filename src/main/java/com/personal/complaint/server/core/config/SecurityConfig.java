package com.personal.complaint.server.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 아래의 requestMatchers 요청은 security 무시
     * @return
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        String[] pathsStr ={
                "/v3/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/swagger-ui/**",
                "/swagger-ui/index.html",
                "/swagger-ui.html",
                "/configuration/security",
                "/webjars/**",
                "/swagger/**",
                "/h2-console/**"
        };
        return (web) -> web.ignoring().requestMatchers(pathsStr);
    }
}
