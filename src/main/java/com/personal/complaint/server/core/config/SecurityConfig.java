package com.personal.complaint.server.core.config;

import com.personal.complaint.server.core.jwt.JwtAccessDeniedHandler;
import com.personal.complaint.server.core.jwt.JwtAuthenticationEntryPoint;
import com.personal.complaint.server.core.jwt.TokenProvider;
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

    private final TokenProvider tokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 아래의 requestMatchers 요청은 security 무시
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        String[] pathStr = {
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
        return (web) -> web.ignoring().requestMatchers(pathStr);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token 사용하는 방식이기 때문에 csrf disable
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // HttpServletRequest를 사용하는 요청들에 대한 접근제한 설정
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/api/auth/login").permitAll() // 로그인 api
                                .requestMatchers("/api/mbr/insertMbrInfoVo").permitAll() // 회원가입 api
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/mbr/listMbrInfo").hasRole("ADMIN") // TEST
                                .anyRequest().permitAll() // 그 외 인증 없이 접근X **permitAll -> authenticated**
                )
                // JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig class 적용
                .apply(new JwtSecurityConfig(tokenProvider));

        return httpSecurity.build();
    }
}
