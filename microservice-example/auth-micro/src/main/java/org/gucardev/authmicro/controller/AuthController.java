package org.gucardev.authmicro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gucardev.authmicro.dto.AuthResponse;
import org.gucardev.authmicro.dto.LoginRequest;
import org.gucardev.authmicro.dto.TokenDto;
import org.gucardev.authmicro.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    @GetMapping("/validate")
    public ResponseEntity<AuthResponse> validate(@RequestParam String token) {
        return ResponseEntity.ok().body(authService.validate(token));
    }
}
