package org.gucardev.securityexample4.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.securityexample4.dto.*;
import org.gucardev.securityexample4.mapper.UserMapper;
import org.gucardev.securityexample4.security.CustomUsernamePasswordAuthenticationToken;
import org.gucardev.securityexample4.service.AuthService;
import org.gucardev.securityexample4.service.JwtDecoderService;
import org.gucardev.securityexample4.service.JwtEncoderService;
import org.gucardev.securityexample4.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtEncoderService tokenService;
    private final JwtDecoderService jwtDecoderService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public TokenDto login(LoginRequest loginRequest) {
        var authObj = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()));

        // update token sign on login
        var sign = userService.updateTokenSign(loginRequest.getUsername());

        var user = userService.getDtoByUsername(loginRequest.getUsername());
        return new TokenDto(tokenService.generateToken(authObj, sign), user);
    }

    @Override
    public AuthResponse validate(String token) {
        if (jwtDecoderService.isTokenExpired(token)) {
            throw new RuntimeException("Token is invalid");
        }
        return new AuthResponse(jwtDecoderService.extractUsername(token), token);
    }

    @Override
    public UserDto getMyself() {
        CustomUsernamePasswordAuthenticationToken jwtAuthentication;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        jwtAuthentication = (CustomUsernamePasswordAuthenticationToken) authentication;
        var user = userService.getByUsername(jwtAuthentication.getName());
        String jwtToken = jwtAuthentication.getToken();
        user.setToken(jwtToken);
        log.info("JWT Token: {}", jwtToken);
        return userMapper.toDto(user);
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        var username = jwtDecoderService.extractUsername(logoutRequest.getToken());
        // update token sign on logout
        userService.updateTokenSign(username);
    }

}
