package org.gucardev.securityexample3.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.securityexample3.dto.RoleDto;
import org.gucardev.securityexample3.entity.Role;
import org.gucardev.securityexample3.mapper.RoleMapper;
import org.gucardev.securityexample3.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public List<RoleDto> getRoles() {
        return repository.findAll().stream().map(RoleMapper::toDto).toList();
    }

    public Optional<Role> getById(Long id) {
        return repository.findById(id);
    }

    public RoleDto createRole(RoleDto dto) {
        return RoleMapper.toDto(repository.save(RoleMapper.toEntity(dto)));
    }

    public RoleDto updateRole(RoleDto dto) {
        var existing = getById(dto.getId());
        if (existing.isEmpty()) {
            throw new EntityNotFoundException();
        }
        RoleMapper.update(existing.get(), dto);
        return RoleMapper.toDto(repository.save(existing.get()));
    }
}
