package org.gucardev.genericexample.spesification;

import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class GenericSpecification<T> {

    public Specification<T> searchBy(List<String> fieldNames, String searchTerm) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(searchTerm) || fieldNames == null || fieldNames.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            String likeTerm = "%" + searchTerm.toLowerCase() + "%";
            List<Predicate> predicates = fieldNames.stream()
                    .map(fieldName -> criteriaBuilder.like(criteriaBuilder.lower(root.get(fieldName)), likeTerm))
                    .toList();

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<T> sortByField(String sortField, Sort.Direction sortDirection) {
        return (root, query, criteriaBuilder) -> {
            Path<Object> sortPath = root.get(sortField);
            Order sortOrder = sortDirection.isAscending() ? criteriaBuilder.asc(sortPath) : criteriaBuilder.desc(sortPath);
            query.orderBy(sortOrder);
            return query.getRestriction();
        };
    }
}
