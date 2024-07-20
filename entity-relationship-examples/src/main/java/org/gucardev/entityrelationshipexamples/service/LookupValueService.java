package org.gucardev.entityrelationshipexamples.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.gucardev.entityrelationshipexamples.dto.LookupValueDTO;
import org.gucardev.entityrelationshipexamples.mapper.LookupValueMapper;
import org.gucardev.entityrelationshipexamples.model.LookupValue;
import org.gucardev.entityrelationshipexamples.repository.LookupValueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LookupValueService {

    private final LookupValueRepository lookupValueRepository;

    private final LookupCategoryService lookupCategoryService;

    private final LookupValueMapper lookupValueMapper = LookupValueMapper.INSTANCE;

    public List<LookupValueDTO> getAllValues() {
        return lookupValueRepository.findAll().stream()
                .map(lookupValueMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<LookupValueDTO> getValueById(Long id) {
        return lookupValueRepository.findById(id)
                .map(lookupValueMapper::toDto);
    }

    public LookupValueDTO createValue(LookupValueDTO valueDTO) {
        LookupValue value = lookupValueMapper.toEntity(valueDTO);
        return lookupValueMapper.toDto(lookupValueRepository.save(value));
    }

    public LookupValueDTO updateValue(Long id, LookupValueDTO updatedValueDTO) {
        Optional<LookupValue> optionalValue = lookupValueRepository.findById(id);

        if (optionalValue.isEmpty()) {
            throw new EntityNotFoundException("LookupValue not found with id " + id);
        }
        LookupValue existingValue = optionalValue.get();
        // Update lookupValue fields
        lookupValueMapper.updateLookupValueFromDto(updatedValueDTO, existingValue);

        // Update category if categoryId is provided
        if (updatedValueDTO.getCategoryId() != null) {
            existingValue.setCategory(lookupCategoryService.getCategoryById(updatedValueDTO.getCategoryId()));
        }

        return lookupValueMapper.toDto(lookupValueRepository.save(existingValue));
    }

    @Transactional
    public void deleteValue(Long id) {
        lookupValueRepository.deleteById(id);
    }

    public List<LookupValueDTO> getValuesByCategoryId(Long categoryId) {
        return lookupValueRepository.findByCategoryId(categoryId).stream()
                .map(lookupValueMapper::toDto)
                // don't need category in response again
                .peek(x -> x.setCategory(null))
                .collect(Collectors.toList());
    }
}
