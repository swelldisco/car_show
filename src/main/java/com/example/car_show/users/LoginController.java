package com.example.car_show.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.car_show.pojo.AccountCredentials;
import com.example.car_show.security.JWTService;


@RestController
public class LoginController {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials){
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(credentials.getUserName(), credentials.getPassword());
        Authentication auth = authManager.authenticate(creds);
        String jwts  = jwtService.getToken(auth.getName());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,"Bearer" + jwts)
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"AUTHORIZATION")
                .build();
    }
    
}
