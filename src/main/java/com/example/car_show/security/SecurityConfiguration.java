package com.example.car_show.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.car_show.users.UserServiceImpl;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserServiceImpl userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .cors(Customizer.withDefaults())
            .csrf(c -> c.disable())
            .httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests(
                auth -> auth.requestMatchers(HttpMethod.GET, "/api/v1/car/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/car/create").hasRole("ADMIN")
                    .anyRequest()
                    .authenticated()
            )
            .build();
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
