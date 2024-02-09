package com.example.car_show.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.car_show.exceptions.AuthEntrypoint;
import com.example.car_show.users.UserServiceImpl;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationFilter authFilter;

    @Autowired
    private AuthEntrypoint authEntrypoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .csrf(c -> c.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                       auth ->auth.
                               requestMatchers(HttpMethod.POST,"/login").permitAll()
                               //requestMatchers("/login", "/api/v1").permitAll()
                               .anyRequest()
                               .authenticated())
                               .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((ex)->ex.authenticationEntryPoint(authEntrypoint))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://127.0.0.1:5173"); 
        configuration.addAllowedMethod("*"); 
        configuration.addAllowedHeader("*"); 
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new 
        UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
