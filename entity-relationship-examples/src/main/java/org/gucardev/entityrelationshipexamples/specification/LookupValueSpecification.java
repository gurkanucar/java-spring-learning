package org.gucardev.entityrelationshipexamples.specification;

import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.gucardev.entityrelationshipexamples.model.LookupValue;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class LookupValueSpecification {

    public static Specification<LookupValue> searchBy(String searchTerm) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(searchTerm)) {
                String likeTerm = "%" + searchTerm.toLowerCase() + "%";
                predicates.add(
                        criteriaBuilder.or(
                                criteriaBuilder.like(
                                        criteriaBuilder.lower(root.get("translations").as(String.class)), likeTerm),
                                criteriaBuilder.like(
                                        criteriaBuilder.lower(root.get("key").as(String.class)), likeTerm),
                                criteriaBuilder.like(
                                        criteriaBuilder.lower(root.get("category").get("key")), likeTerm)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
