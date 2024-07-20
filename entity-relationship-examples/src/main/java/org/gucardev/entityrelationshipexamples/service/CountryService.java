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

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    public List<CountryDTO> getAll() {
        return countryRepository.findAll().stream()
                .map(countryMapper::toDto)
                .collect(Collectors.toList());
    }

    public Country getById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found"));
    }

    public Optional<CountryDTO> getDtoById(Long id) {
        return countryRepository.findById(id)
                .map(countryMapper::toDto);
    }

    public CountryDTO create(CountryDTO countryDTO) {
        Country country = countryMapper.toEntity(countryDTO);
        country = countryRepository.save(country);
        return countryMapper.toDto(country);
    }

    public CountryDTO update(Long id, CountryDTO countryDTO) {
        Optional<Country> optionalValue = countryRepository.findById(id);
        if (optionalValue.isEmpty()) {
            throw new EntityNotFoundException("Country not found with id " + id);
        }
        Country existing = optionalValue.get();
        countryMapper.updateCountryFromDto(countryDTO, existing);
        return countryMapper.toDto(countryRepository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
