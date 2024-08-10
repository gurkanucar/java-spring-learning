package org.gucardev.securityexample2.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.securityexample2.dto.UserDto;
import org.gucardev.securityexample2.dto.UserRequest;
import org.gucardev.securityexample2.entity.User;
import org.gucardev.securityexample2.mapper.UserMapper;
import org.gucardev.securityexample2.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getUsers() {
        return repository.findAll().stream().map(UserMapper::toDto).toList();
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<User> getByUsername(String username) {
        return repository.findByUsernameAndIsEnabledTrue(username);
    }

    public void createUser(UserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserMapper.toDto(repository.save(UserMapper.toEntity(request)));
    }

    public UserDto updateUser(UserDto dto) {
        var existing = getById(dto.getId());
        if (existing.isEmpty()) {
            throw new EntityNotFoundException();
        }
        UserMapper.update(existing.get(), dto);
        return UserMapper.toDto(repository.save(existing.get()));
    }
}
