package com.cinebook.cinebookback.entity.specification;

import com.cinebook.cinebookback.DTO.UserFilterRequestDTO;
import com.cinebook.cinebookback.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {
    public static Specification<User> filterBy(UserFilterRequestDTO filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getUsername() != null) {
                predicates.add(criteriaBuilder.like(root.get("username"), "%" + filter.getUsername() + "%"));
            }
            if (filter.getEmail() != null) {
                predicates.add(criteriaBuilder.equal(root.get("email"), filter.getEmail()));
            }
            if (filter.getRole() != null) {
                predicates.add(criteriaBuilder.equal(root.get("role"), filter.getRole()));
            }
            if (filter.getIsPremium() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isPremium"), filter.getIsPremium()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}