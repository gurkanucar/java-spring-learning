package org.gucardev.genericexample.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gucardev.genericexample.mapper.GenericMapper;
import org.gucardev.genericexample.repository.GenericRepository;
import org.gucardev.genericexample.spesification.GenericSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl<E, D, ID> {

    private final GenericRepository<E, ID> repository;
    private final GenericMapper<E, D> mapper;

    protected GenericServiceImpl(GenericRepository<E, ID> repository, GenericMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<D> getAll(String searchTerm, Pageable pageable, List<String> searchableFields,
                          String sortField, Sort.Direction sortDirection) {
        Specification<E> searchSpec = new GenericSpecification<E>().searchBy(searchableFields, searchTerm);
        Specification<E> sortSpec = new GenericSpecification<E>().sortByField(sortField, sortDirection);
        Specification<E> combinedSpec = Specification.where(searchSpec).and(sortSpec);
        return repository.findAll(combinedSpec, pageable)
                .map(mapper::toDto);
    }

    public E getById(ID id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(id.toString()));
    }

    public Optional<E> getByIdOptional(ID id) {
        return repository.findById(id);
    }

    public D getDtoById(ID id) {
        return mapper.toDto(getById(id));
    }

    public Optional<D> getDtoByIdOptional(ID id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public D create(D dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public List<D> create(List<D> dtoList) {
        return repository.saveAll(dtoList.stream()
                        .map(mapper::toEntity).toList())
                .stream().map(mapper::toDto).toList();
    }

    public D update(ID id, D dto) {
        var existing = getById(id);
        mapper.partialUpdate(dto, existing);
        return mapper.toDto(repository.save(existing));
    }

    @Transactional
    public void delete(ID id) {
        repository.deleteById(id);
    }
}
