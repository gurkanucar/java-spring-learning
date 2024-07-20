package org.gucardev.entityrelationshipexamples.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.gucardev.entityrelationshipexamples.dto.CountryDTO;
import org.gucardev.entityrelationshipexamples.mapper.CountryMapper;
import org.gucardev.entityrelationshipexamples.model.Country;
import org.gucardev.entityrelationshipexamples.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository repository;

    private final CountryMapper mapper;

    public List<CountryDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public Country getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found"));
    }

    public Optional<CountryDTO> getDtoById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    public CountryDTO create(CountryDTO dto) {
        Country entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public CountryDTO update(Long id, CountryDTO dto) {
        Optional<Country> optionalRecord = repository.findById(id);
        if (optionalRecord.isEmpty()) {
            throw new EntityNotFoundException("Country not found with id " + id);
        }
        Country existing = optionalRecord.get();
        mapper.updateFromDto(dto, existing);
        return mapper.toDto(repository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
