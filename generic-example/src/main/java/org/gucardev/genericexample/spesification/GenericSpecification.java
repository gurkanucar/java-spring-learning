package org.gucardev.genericexample.spesification;

import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class GenericSpecification<T> {

    public Specification<T> searchBy(List<String> fieldNames, String searchTerm) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isBlank(searchTerm) || fieldNames == null || fieldNames.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            String likeTerm = "%" + searchTerm.toLowerCase() + "%";
            Map<String, Join<?, ?>> joins = new HashMap<>();

            List<Predicate> predicates = fieldNames.stream()
                    .map(fieldName -> {
                        Path<?> fieldPath = getPath(root, fieldName, joins);
                        if (fieldPath == null) {
                            return null;
                        }
                        Class<?> fieldType = fieldPath.getJavaType();
                        if (String.class.equals(fieldType)) {
                            return criteriaBuilder.like(
                                    criteriaBuilder.lower(fieldPath.as(String.class)), likeTerm);
                        } else if (Number.class.isAssignableFrom(fieldType)) {
                            try {
                                Number number = NumberFormat.getInstance().parse(searchTerm);
                                return criteriaBuilder.equal(fieldPath, number);
                            } catch (ParseException e) {
                                return null;
                            }
                        } else if (Boolean.class.equals(fieldType) || boolean.class.equals(fieldType)) {
                            if ("true".equalsIgnoreCase(searchTerm) || "false".equalsIgnoreCase(searchTerm)) {
                                Boolean boolValue = Boolean.valueOf(searchTerm);
                                return criteriaBuilder.equal(fieldPath, boolValue);
                            } else {
                                return null;
                            }
                        } else if (UUID.class.equals(fieldType)) {
                            try {
                                UUID uuidValue = UUID.fromString(searchTerm);
                                return criteriaBuilder.equal(fieldPath, uuidValue);
                            } catch (IllegalArgumentException e) {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .toList();

            if (predicates.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }


    private Path<?> getPath(From<?, ?> from, String fieldName, Map<String, Join<?, ?>> joins) {
        String[] parts = fieldName.split("\\.");
        Path<?> path = from;
        StringBuilder joinNameBuilder = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];

            if (i < parts.length - 1) {
                // Build the join name for the map key
                if (!joinNameBuilder.isEmpty()) {
                    joinNameBuilder.append(".");
                }
                joinNameBuilder.append(part);
                String joinName = joinNameBuilder.toString();

                // Check if we've already joined this path
                if (joins.containsKey(joinName)) {
                    path = joins.get(joinName);
                } else {
                    Join<?, ?> join = ((From<?, ?>) path).join(part, JoinType.LEFT);
                    joins.put(joinName, join);
                    path = join;
                }
            } else {
                path = path.get(part);
            }
        }
        return path;
    }


    public Specification<T> sortByField(String sortField, Sort.Direction sortDirection) {
        // Set default sort field and direction if not provided
        if (StringUtils.isBlank(sortField)) {
            sortField = "updatedDateTime";
        }
        if (sortDirection == null) {
            sortDirection = Sort.Direction.DESC;
        }

        String finalSortField = sortField;
        Sort.Direction finalSortDirection = sortDirection;
        return (root, query, criteriaBuilder) -> {
            Path<Object> sortPath = root.get(finalSortField);
            Order sortOrder = finalSortDirection.isAscending() ? criteriaBuilder.asc(sortPath) : criteriaBuilder.desc(sortPath);
            query.orderBy(sortOrder);
            return query.getRestriction();
        };
    }

}
