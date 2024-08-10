package org.gucardev.securityexample4.service;

import org.gucardev.securityexample4.dto.*;

public interface AuthService {

    TokenDto login(LoginRequest loginRequest);

    AuthResponse validate(String token);

    UserDto getMyself();

    void logout(LogoutRequest logoutRequest);
}
