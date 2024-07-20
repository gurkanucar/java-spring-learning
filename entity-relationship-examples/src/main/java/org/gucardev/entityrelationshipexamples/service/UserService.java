package org.gucardev.entityrelationshipexamples.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.gucardev.entityrelationshipexamples.dto.UserDTO;
import org.gucardev.entityrelationshipexamples.mapper.UserMapper;
import org.gucardev.entityrelationshipexamples.model.User;
import org.gucardev.entityrelationshipexamples.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final LookupValueService lookupValueService;
    private final UserMapper mapper = UserMapper.INSTANCE;

    public List<UserDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }


    public Optional<UserDTO> getDtoById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    public UserDTO create(UserDTO dto) {
        User entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public UserDTO update(Long id, UserDTO dto) {
        Optional<User> optionalRecord = repository.findById(id);

        if (optionalRecord.isEmpty()) {
            throw new EntityNotFoundException("User not found with id " + id);
        }

        User existingUser = optionalRecord.get();

        // update fields and ignore entity-relation fields
        mapper.updateFromDto(dto, existingUser);

        // set related fields
        if (dto.getOccupationId() != null) {
            existingUser.setOccupation(lookupValueService.getById(dto.getOccupationId()));
        } else {
            existingUser.setOccupation(null);
        }

        if (dto.getStatusId() != null) {
            existingUser.setStatus(lookupValueService.getById(dto.getStatusId()));
        } else {
            existingUser.setStatus(null);
        }


        return mapper.toDto(repository.save(existingUser));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
