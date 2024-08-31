package org.gucardev.genericexample.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GenericService<E, D, ID> {
    Page<D> getAll(String searchTerm, Pageable pageable, List<String> searchableFields);
    E getById(ID id);
    Optional<E> getByIdOptional(ID id);
    D getDtoById(ID id);
    Optional<D> getDtoByIdOptional(ID id);
    D create(D dto);
    List<D> create(List<D> dtoList);
    D update(ID id, D dto);
    void delete(ID id);
}
