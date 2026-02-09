package com.abhay.blog.blogapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.abhay.blog.blogapplication.security.CoustomUserDetailService;

import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    
    @Autowired
    private CoustomUserDetailService coustomUserDetailService;

    public SecurityConfig(CoustomUserDetailService coustomUserDetailService){
        this.coustomUserDetailService = coustomUserDetailService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
        .csrf(csrf-> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .anyRequest()
            .authenticated()
        )
        .httpBasic(Customizer.withDefaults());
        


        return http.build();
        
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}

