package org.gucardev.entityrelationshipexamples.controller;

import lombok.RequiredArgsConstructor;
import org.gucardev.entityrelationshipexamples.dto.LookupCategoryDTO;
import org.gucardev.entityrelationshipexamples.service.LookupCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/lookup-category")
public class LookupCategoryController {

    private final LookupCategoryService lookupCategoryService;

    @GetMapping
    public List<LookupCategoryDTO> getAllCategories() {
        return lookupCategoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LookupCategoryDTO> getCategoryById(@PathVariable Long id) {
        return lookupCategoryService.getDtoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public LookupCategoryDTO createCategory(@RequestBody LookupCategoryDTO categoryDTO) {
        return lookupCategoryService.create(categoryDTO);
    }


    @PostMapping("/with-values")
    public ResponseEntity<LookupCategoryDTO> createCountryWithCities(@RequestBody LookupCategoryDTO categoryDTO) {
        LookupCategoryDTO newCategory = lookupCategoryService.createLookupCategoryWithValues(categoryDTO);
        return ResponseEntity.ok(newCategory);
    }


    @PutMapping("/{id}")
    public ResponseEntity<LookupCategoryDTO> updateCategory(@PathVariable Long id, @RequestBody LookupCategoryDTO updatedCategoryDTO) {
        return ResponseEntity.ok(lookupCategoryService.update(id, updatedCategoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        lookupCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
