package org.gucardev.securityexample4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gucardev.securityexample4.dto.*;
import org.gucardev.securityexample4.service.AuthService;
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

    @GetMapping("/get-myself")
    public ResponseEntity<UserDto> generateServiceToken() {
        return ResponseEntity.ok().body(authService.getMyself());
    }

    @GetMapping("/validate")
    public ResponseEntity<AuthResponse> validate(@RequestParam String token) {
        return ResponseEntity.ok().body(authService.validate(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LogoutRequest logoutRequest) {
        authService.logout(logoutRequest);
        return ResponseEntity.ok().build();
    }

}
