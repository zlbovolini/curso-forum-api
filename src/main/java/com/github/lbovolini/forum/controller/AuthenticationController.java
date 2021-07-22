package com.github.lbovolini.forum.controller;

import com.github.lbovolini.forum.request.LoginRequest;
import com.github.lbovolini.forum.response.TokenResponse;
import com.github.lbovolini.forum.service.TokenService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Profile("prod")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken userCredentials = loginRequest.toUserCredentials();

        try {
            Authentication authentication = authenticationManager.authenticate(userCredentials);
            String token = tokenService.generate(authentication);
            TokenResponse tokenResponse = new TokenResponse(token, "Bearer");

            return ResponseEntity.ok(tokenResponse);
        } catch (AuthenticationException ae) {
            return ResponseEntity.badRequest().build();
        }
    }
}
