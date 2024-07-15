package org.gucardev.authmicro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gucardev.authmicro.dto.LoginRequest;
import org.gucardev.authmicro.dto.TokenDto;
import org.gucardev.authmicro.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }
}
