package org.gucardev.entityrelationshipexamples.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.gucardev.entityrelationshipexamples.dto.LookupValueDTO;
import org.gucardev.entityrelationshipexamples.mapper.LookupValueMapper;
import org.gucardev.entityrelationshipexamples.model.LookupValue;
import org.gucardev.entityrelationshipexamples.repository.LookupValueRepository;
import org.gucardev.entityrelationshipexamples.specification.LookupValueSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LookupValueService {

    private final LookupValueRepository repository;

    private final LookupCategoryService lookupCategoryService;

    private final LookupValueMapper mapper = LookupValueMapper.INSTANCE;

    public Page<LookupValueDTO> getAll(String searchTerm, Pageable pageable) {
        Specification<LookupValue> spec = Specification.where(LookupValueSpecification.searchBy(searchTerm));
        return repository.findAll(spec, pageable).map(mapper::toDto);
    }

    public LookupValue getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Value with id " + id + " not found"));
    }

    public Optional<LookupValueDTO> getDtoById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    public LookupValueDTO create(LookupValueDTO dto) {
        LookupValue entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public LookupValueDTO update(Long id, LookupValueDTO dto) {
        Optional<LookupValue> optionalRecord = repository.findById(id);

        if (optionalRecord.isEmpty()) {
            throw new EntityNotFoundException("LookupValue not found with id " + id);
        }
        LookupValue existing = optionalRecord.get();
        // Update lookupValue fields
        mapper.updateFromDto(dto, existing);

        // Update category if categoryId is provided
        if (dto.getCategoryId() != null) {
            existing.setCategory(lookupCategoryService.getById(dto.getCategoryId()));
        }

        //update translations
        //ignore translations in mapper updateFromDto method before set them
        mapper.updateTranslationsMap(dto, existing);

        return mapper.toDto(repository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<LookupValueDTO> getValuesByCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId).stream()
                .map(mapper::toDto)
                // don't need category in response again
                .peek(x -> x.setCategory(null))
                .collect(Collectors.toList());
    }


}
