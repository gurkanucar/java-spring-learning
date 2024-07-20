package org.gucardev.entityrelationshipexamples.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.gucardev.entityrelationshipexamples.dto.LookupCategoryDTO;
import org.gucardev.entityrelationshipexamples.mapper.LookupCategoryMapper;
import org.gucardev.entityrelationshipexamples.model.LookupCategory;
import org.gucardev.entityrelationshipexamples.repository.LookupCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LookupCategoryService {

    private final LookupCategoryRepository repository;

    private final LookupCategoryMapper mapper = LookupCategoryMapper.INSTANCE;

    public List<LookupCategoryDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public LookupCategory getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find category with id: " + id));
    }

    public Optional<LookupCategoryDTO> getDtoById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    public LookupCategoryDTO create(LookupCategoryDTO dto) {
        LookupCategory entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public LookupCategoryDTO update(Long id, LookupCategoryDTO dto) {
        Optional<LookupCategory> optionalRecord = repository.findById(id);
        if (optionalRecord.isEmpty()) {
            throw new EntityNotFoundException("LookupCategory not found with id " + id);
        }
        LookupCategory existing = optionalRecord.get();
        // Update lookupValue fields
        mapper.updateFromDto(dto, existing);

        return mapper.toDto(repository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public LookupCategoryDTO createLookupCategoryWithValues(LookupCategoryDTO dto) {
        LookupCategory entity = mapper.toEntity(dto);
        mapper.linkLookupValues(entity);
        return mapper.toDto(repository.save(entity));
    }
}
