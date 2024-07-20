package org.gucardev.entityrelationshipexamples.service;

import jakarta.persistence.EntityNotFoundException;
import org.gucardev.entityrelationshipexamples.dto.LookupCategoryDTO;
import org.gucardev.entityrelationshipexamples.mapper.LookupCategoryMapper;
import org.gucardev.entityrelationshipexamples.model.LookupCategory;
import org.gucardev.entityrelationshipexamples.repository.LookupCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LookupCategoryService {

    @Autowired
    private LookupCategoryRepository lookupCategoryRepository;

    private final LookupCategoryMapper lookupCategoryMapper = LookupCategoryMapper.INSTANCE;

    public List<LookupCategoryDTO> getAllCategories() {
        return lookupCategoryRepository.findAll().stream()
                .map(lookupCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public LookupCategory getCategoryById(Long id) {
        return lookupCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find category with id: " + id));
    }


    public Optional<LookupCategoryDTO> getCategoryDtoById(Long id) {
        return lookupCategoryRepository.findById(id)
                .map(lookupCategoryMapper::toDto);
    }

    public LookupCategoryDTO createCategory(LookupCategoryDTO categoryDTO) {
        LookupCategory category = lookupCategoryMapper.toEntity(categoryDTO);
        return lookupCategoryMapper.toDto(lookupCategoryRepository.save(category));
    }

    public LookupCategoryDTO updateCategory(Long id, LookupCategoryDTO updatedCategoryDTO) {
        Optional<LookupCategory> optionalCategory = lookupCategoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            LookupCategory existingCategory = optionalCategory.get();
            existingCategory.setName(updatedCategoryDTO.getName());
            // Do not update nested lookupValues here
            return lookupCategoryMapper.toDto(lookupCategoryRepository.save(existingCategory));
        } else {
            throw new EntityNotFoundException("LookupCategory not found with id " + id);
        }
    }

    @Transactional
    public void deleteCategory(Long id) {
        lookupCategoryRepository.deleteById(id);
    }
}
