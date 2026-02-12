package com.abhay.blog.blogapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.abhay.blog.blogapplication.security.CoustomUserDetailService;
import com.abhay.blog.blogapplication.security.JwtAuthenticationEntryPoint;
import com.abhay.blog.blogapplication.security.JwtAuthenticationFilter;

import org.springframework.security.authentication.AuthenticationManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig{
    
    @Autowired
    private CoustomUserDetailService coustomUserDetailService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(CoustomUserDetailService coustomUserDetailService ,
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
        JwtAuthenticationFilter jwtAuthenticationFilter){
        this.coustomUserDetailService = coustomUserDetailService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
        .csrf(csrf-> csrf.disable())
        .exceptionHandling(exception ->
            exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
        )
        .sessionManagement(session ->
            session.sessionCreationPolicy(
                org.springframework.security.config.http.SessionCreationPolicy.STATELESS)
            )
        
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/**").permitAll()
            .anyRequest()
            .authenticated()
    
        )
        .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
        
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager ( AuthenticationConfiguration config) throws Exception{

            return config.getAuthenticationManager();
        }
    

}

