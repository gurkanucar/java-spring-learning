package org.gucardev.genericexample.controller;

import jakarta.validation.Valid;
import org.gucardev.genericexample.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


public abstract class GenericRestController<E, D, ID> {

    private final GenericService<E, D, ID> service;

    protected GenericRestController(GenericService<E, D, ID> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<D>> getAll(
            @RequestParam(required = false) String searchTerm,
            Pageable pageable,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortDir) {
        List<String> searchableFields = getSearchableFields();
        Sort.Direction sortDirection = Sort.Direction.fromOptionalString(sortDir).orElse(Sort.Direction.ASC);
        Page<D> results = service.getAll(searchTerm, pageable, searchableFields, sortField, sortDirection);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable ID id) {
        Optional<D> dto = service.getDtoByIdOptional(id);
        return dto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<D> create(@Valid @RequestBody D dto) {
        D createdDto = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @PostMapping("/list")
    public ResponseEntity<List<D>> create(@Valid @RequestBody List<D> dtoList) {
        List<D> createdDto = service.create(dtoList);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable ID id, @Valid @RequestBody D dto) {
        D updatedDto = service.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    protected abstract List<String> getSearchableFields();

}
