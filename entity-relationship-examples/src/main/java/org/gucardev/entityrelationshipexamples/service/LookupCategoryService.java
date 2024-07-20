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

    private final LookupCategoryRepository lookupCategoryRepository;

    private final LookupCategoryMapper lookupCategoryMapper = LookupCategoryMapper.INSTANCE;

    public List<LookupCategoryDTO> getAll() {
        return lookupCategoryRepository.findAll().stream()
                .map(lookupCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public LookupCategory getById(Long id) {
        return lookupCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find category with id: " + id));
    }

    public Optional<LookupCategoryDTO> getDtoById(Long id) {
        return lookupCategoryRepository.findById(id)
                .map(lookupCategoryMapper::toDto);
    }

    public LookupCategoryDTO create(LookupCategoryDTO categoryDTO) {
        LookupCategory category = lookupCategoryMapper.toEntity(categoryDTO);
        return lookupCategoryMapper.toDto(lookupCategoryRepository.save(category));
    }

    public LookupCategoryDTO update(Long id, LookupCategoryDTO updateRequest) {
        Optional<LookupCategory> optionalValue = lookupCategoryRepository.findById(id);
        if (optionalValue.isEmpty()) {
            throw new EntityNotFoundException("LookupCategory not found with id " + id);
        }
        LookupCategory existing = optionalValue.get();
        // Update lookupValue fields
        lookupCategoryMapper.updateLookupValueFromDto(updateRequest, existing);

        return lookupCategoryMapper.toDto(lookupCategoryRepository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        lookupCategoryRepository.deleteById(id);
    }
}
