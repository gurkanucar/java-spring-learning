package org.gucardev.authmicro.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.authmicro.dto.AuthResponse;
import org.gucardev.authmicro.dto.LoginRequest;
import org.gucardev.authmicro.dto.TokenDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final JwtEncoderService tokenService;
    private final JwtDecoderService jwtDecoderService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public TokenDto login(LoginRequest loginRequest) {
        try {
            var authObj = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()));
            return new TokenDto(tokenService.generateToken(authObj));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public AuthResponse validate(String token) {
        if (jwtDecoderService.isTokenExpired(token)) {
            throw new RuntimeException("Token is invalid");
        }
        return new AuthResponse(jwtDecoderService.extractUsername(token), token);
    }

    public TokenDto generateServiceToken(LoginRequest loginRequest) {
        try {
            var service = userService.validateAndReturnService(loginRequest.getUsername(), loginRequest.getPassword());
            return new TokenDto(tokenService.generateServiceToken(service));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
