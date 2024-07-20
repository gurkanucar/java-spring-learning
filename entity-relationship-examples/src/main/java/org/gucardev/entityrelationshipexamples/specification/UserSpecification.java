package org.gucardev.entityrelationshipexamples.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.gucardev.entityrelationshipexamples.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserSpecification {

    public static Specification<User> searchBy(String searchTerm, Long userItselfId, LocalDateTime startDate, LocalDateTime endDate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            addSearchTermPredicate(predicates, searchTerm, root, criteriaBuilder);
            excludeSelfPredicate(predicates, userItselfId, root, criteriaBuilder);
            addDateRangePredicate(predicates, startDate, endDate, root, criteriaBuilder);
            query.orderBy(criteriaBuilder.desc(root.get("createDate")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static void addSearchTermPredicate(List<Predicate> predicates, String searchTerm, Root<User> root, CriteriaBuilder criteriaBuilder) {
        if (StringUtils.isNotBlank(searchTerm)) {
            String likeTerm = "%" + searchTerm.toLowerCase() + "%";
            predicates.add(
                    criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likeTerm),
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("surname")), likeTerm),
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), likeTerm),
                            criteriaBuilder.like(criteriaBuilder.lower(
                                    criteriaBuilder.concat(criteriaBuilder.concat(root.get("name"), " "), root.get("surname"))), likeTerm),
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("occupation").get("translations").as(String.class)), likeTerm)
                    ));
        }
    }

    private static void excludeSelfPredicate(List<Predicate> predicates, Long userItselfId, Root<User> root, CriteriaBuilder criteriaBuilder) {
        if (userItselfId != null) {
            predicates.add(criteriaBuilder.notEqual(root.get("id"), userItselfId));
        }
    }

    private static void addDateRangePredicate(List<Predicate> predicates, LocalDateTime startDate, LocalDateTime endDate, Root<User> root, CriteriaBuilder criteriaBuilder) {
        if (Objects.nonNull(startDate)) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), startDate));
        }
        if (Objects.nonNull(endDate)) {
            predicates.add(criteriaBuilder.lessThan(root.get("createDate"), endDate));
        }
    }

    public static Specification<User> sortByField(String sortField, Sort.Direction sortDirection) {
        return (root, query, criteriaBuilder) -> {
            switch (sortField) {
                case "name":
                case "createDate":
                case "updateDate":
                case "email":
                case "id":
                    query.orderBy(sortDirection.isAscending() ? criteriaBuilder.asc(root.get(sortField)) : criteriaBuilder.desc(root.get(sortField)));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported sort field: " + sortField);
            }
            return query.getRestriction();
        };
    }
}
