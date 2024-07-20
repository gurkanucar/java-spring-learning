package org.gucardev.entityrelationshipexamples.controller;

import jakarta.persistence.EntityNotFoundException;
import org.gucardev.entityrelationshipexamples.dto.LookupCategoryDTO;
import org.gucardev.entityrelationshipexamples.service.LookupCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lookupCategories")
public class LookupCategoryController {

    @Autowired
    private LookupCategoryService lookupCategoryService;

    @GetMapping
    public List<LookupCategoryDTO> getAllCategories() {
        return lookupCategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LookupCategoryDTO> getCategoryById(@PathVariable Long id) {
        Optional<LookupCategoryDTO> categoryDTO = lookupCategoryService.getCategoryDtoById(id);
        if (categoryDTO.isPresent()) {
            return ResponseEntity.ok(categoryDTO.get());
        } else {
            throw new EntityNotFoundException("LookupCategory not found with id " + id);
        }
    }

    @PostMapping
    public LookupCategoryDTO createCategory(@RequestBody LookupCategoryDTO categoryDTO) {
        return lookupCategoryService.createCategory(categoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LookupCategoryDTO> updateCategory(@PathVariable Long id, @RequestBody LookupCategoryDTO updatedCategoryDTO) {
        return ResponseEntity.ok(lookupCategoryService.updateCategory(id, updatedCategoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        lookupCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
