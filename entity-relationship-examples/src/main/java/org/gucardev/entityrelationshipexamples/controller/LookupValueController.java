package org.gucardev.entityrelationshipexamples.controller;

import lombok.RequiredArgsConstructor;
import org.gucardev.entityrelationshipexamples.dto.LookupValueDTO;
import org.gucardev.entityrelationshipexamples.service.LookupValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/lookup-value")
public class LookupValueController {

    private final LookupValueService lookupValueService;

    @GetMapping
    public List<LookupValueDTO> getAllValues() {
        return lookupValueService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LookupValueDTO> getValueById(@PathVariable Long id) {
        return lookupValueService.getDtoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public LookupValueDTO createValue(@RequestBody LookupValueDTO valueDTO) {
        return lookupValueService.create(valueDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LookupValueDTO> updateValue(@PathVariable Long id, @RequestBody LookupValueDTO updatedValueDTO) {
        return ResponseEntity.ok(lookupValueService.update(id, updatedValueDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteValue(@PathVariable Long id) {
        lookupValueService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-category/{categoryId}")
    public List<LookupValueDTO> getValuesByCategoryId(@PathVariable Long categoryId) {
        return lookupValueService.getValuesByCategoryId(categoryId);
    }
}
