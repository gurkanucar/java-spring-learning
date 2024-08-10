package org.gucardev.securityexample4.service;

import org.gucardev.securityexample4.dto.UserDto;
import org.gucardev.securityexample4.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<User> getById(Long id);

    User getByUsername(String username);

    UserDto getDtoByUsername(String username);

    UUID updateTokenSign(String username);

    User createUser(User user);

    User validateAndReturnService(String username, String password);

}
