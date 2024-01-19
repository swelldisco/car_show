package com.example.car_show.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


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
                .cors(Customizer.withDefaults())
                .csrf(c -> c.disable())
                .authorizeHttpRequests(
                       auth ->auth.
                                requestMatchers(HttpMethod.POST,"/login").permitAll()
                                // .requestMatchers(HttpMethod.GET, "/api/v1/car/*").hasAnyRole("USER", "ADMIN")
                                // .requestMatchers(HttpMethod.POST, "/api/v1/car/create").hasRole("ADMIN")
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

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails admin = User.builder()
    //         .username("admin")
    //         .password(bCryptPasswordEncoder().encode("password"))
    //         .roles("ADMIN")
    //         .build();

    //     var user = User.builder()
    //         .username("user")
    //         .password(bCryptPasswordEncoder().encode("userPassword"))
    //         .roles("USER")
    //         .build();

    //     return new InMemoryUserDetailsManager(user, admin);
    // }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }

}
