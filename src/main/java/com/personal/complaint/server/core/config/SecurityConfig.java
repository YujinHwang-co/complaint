package com.personal.complaint.server.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
     *
     * @return
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        String[] pathsStr = {
                "/v3/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/swagger-ui/**",
                "/swagger-ui/index.html",
                "/swagger-ui.html",
                "/configuration/security",
                "/webjars/**",
                "/swagger/**"
        };
        return (web) -> web.ignoring().requestMatchers(pathsStr);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                // 시큐리티는 기본적으로 세션을 사용
                .sessionManagement((sessionManagement) ->
                        // 세션 설정을 Stateless 로 설정
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/api/admin/auth/**").permitAll()
                                .anyRequest().permitAll()
                );

        return http.build();
    }
}
