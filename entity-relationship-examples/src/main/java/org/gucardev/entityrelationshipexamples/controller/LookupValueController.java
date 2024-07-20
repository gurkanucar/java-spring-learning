package org.gucardev.entityrelationshipexamples.controller;

import jakarta.persistence.EntityNotFoundException;
import org.gucardev.entityrelationshipexamples.dto.LookupValueDTO;
import org.gucardev.entityrelationshipexamples.service.LookupValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lookupValues")
public class LookupValueController {

    @Autowired
    private LookupValueService lookupValueService;

    @GetMapping
    public List<LookupValueDTO> getAllValues() {
        return lookupValueService.getAllValues();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LookupValueDTO> getValueById(@PathVariable Long id) {
        Optional<LookupValueDTO> valueDTO = lookupValueService.getValueById(id);
        if (valueDTO.isPresent()) {
            return ResponseEntity.ok(valueDTO.get());
        } else {
            throw new EntityNotFoundException("LookupValue not found with id " + id);
        }
    }

    @PostMapping
    public LookupValueDTO createValue(@RequestBody LookupValueDTO valueDTO) {
        return lookupValueService.createValue(valueDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LookupValueDTO> updateValue(@PathVariable Long id, @RequestBody LookupValueDTO updatedValueDTO) {
        return ResponseEntity.ok(lookupValueService.updateValue(id, updatedValueDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteValue(@PathVariable Long id) {
        lookupValueService.deleteValue(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{categoryId}")
    public List<LookupValueDTO> getValuesByCategoryId(@PathVariable Long categoryId) {
        return lookupValueService.getValuesByCategoryId(categoryId);
    }
}
