package com.example.jwt_security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final TokenFilter filter;
    private final AuthenticationProvider provider;
    private final TokenException exception;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(exception)
                .and();
        http
                .csrf()
                .disable()
                .cors() // CORS ayarı ekleniyor
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**")
                .permitAll()
                .requestMatchers("/api/roles/**")
                .permitAll()
                .requestMatchers("/api/users/**")
                .permitAll()
                //.requestMatchers("/api/event/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(provider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}