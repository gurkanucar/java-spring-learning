package org.gucardev.authmicro.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.authmicro.model.Role;
import org.gucardev.authmicro.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;


    public Optional<Role> getById(Long id) {
        return repository.findById(id);
    }

    public Role createRole(Role role) {
        return repository.save(role);
    }

    public Role findByName(String roleName) {
        return repository.findByName(roleName);
    }
}
