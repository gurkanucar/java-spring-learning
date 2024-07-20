package org.gucardev.entityrelationshipexamples.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.gucardev.entityrelationshipexamples.dto.CityDTO;
import org.gucardev.entityrelationshipexamples.mapper.CityMapper;
import org.gucardev.entityrelationshipexamples.model.City;
import org.gucardev.entityrelationshipexamples.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository repository;
    private final CountryService countryService;
    private final CityMapper mapper;

    public List<CityDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public City getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found"));
    }

    public Optional<CityDTO> getDtoById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    public CityDTO create(CityDTO dto) {
        City entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public CityDTO update(Long id, CityDTO dto) {
        Optional<City> optionalRecord = repository.findById(id);

        if (optionalRecord.isEmpty()) {
            throw new EntityNotFoundException("LookupValue not found with id " + id);
        }
        City existing = optionalRecord.get();
        // Update other fields
        mapper.updateFromDto(dto, existing);

        // Update country if countryId is provided
        if (dto.getCountryId() != null) {
            existing.setCountry(countryService.getById(dto.getCountryId()));
        }

        return mapper.toDto(repository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<CityDTO> getCitiesByCountry(Long countryId) {
        return repository.findByCountryId(countryId).stream()
                .map(mapper::toDto)
                // don't need category in response again
                .peek(x -> x.setCountry(null))
                .collect(Collectors.toList());
    }

}
