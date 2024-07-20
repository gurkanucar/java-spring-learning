package org.gucardev.entityrelationshipexamples.controller;

import lombok.RequiredArgsConstructor;
import org.gucardev.entityrelationshipexamples.dto.CityDTO;
import org.gucardev.entityrelationshipexamples.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {
        List<CityDTO> cities = cityService.getAll();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
        return cityService.getDtoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-country/{countryId}")
    public ResponseEntity<List<CityDTO>> getCitiesByCountry(@PathVariable Long countryId) {
        List<CityDTO> cities = cityService.getCitiesByCountry(countryId);
        return ResponseEntity.ok(cities);
    }

    @PostMapping
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO) {
        CityDTO newCity = cityService.create(cityDTO);
        return ResponseEntity.ok(newCity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        CityDTO updatedCity = cityService.update(id, cityDTO);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
