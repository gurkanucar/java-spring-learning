package org.gucardev.securityexample4.service.impl;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.securityexample4.dto.UserDto;
import org.gucardev.securityexample4.mapper.UserMapper;
import org.gucardev.securityexample4.model.User;
import org.gucardev.securityexample4.repository.UserRepository;
import org.gucardev.securityexample4.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    @Override
    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User getByUsername(String username) {
        var user = repository.findByUsernameAndIsEnabledTrue(username);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("user not found!");
        }
        return user.get();
    }

    @Override
    public UserDto getDtoByUsername(String username) {
        return mapper.toDto(getByUsername(username));
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public UUID updateTokenSign(String username) {
        User user = getByUsername(username);
        var sign = UUID.randomUUID();
        user.setTokenSign(sign);
        repository.save(user);
        return sign;
    }

    @Override
    public User validateAndReturnService(String username, String password) {
        var service = getByUsername(username);
        if (!passwordEncoder.matches(password, service.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return service;
    }

}
