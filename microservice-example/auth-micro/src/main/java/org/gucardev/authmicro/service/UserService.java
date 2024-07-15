package org.gucardev.authmicro.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.authmicro.model.User;
import org.gucardev.authmicro.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    public User getByUsername(String username) {
        var user = repository.findByUsernameAndIsEnabledTrue(username);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("user not found!");
        }
        return user.get();
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

}
