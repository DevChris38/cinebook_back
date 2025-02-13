package com.cinebook.cinebookback.entity.specification;

import com.cinebook.cinebookback.DTO.UserFilterRequestDTO;
import com.cinebook.cinebookback.entity.Job;
import com.cinebook.cinebookback.entity.Region;
import com.cinebook.cinebookback.entity.User;
import jakarta.persistence.criteria.Join;
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
            if (filter.getJob() != null) {
                Join<User, Job> jobJoin = root.join("jobs");
                predicates.add(criteriaBuilder.equal(jobJoin.get("fullName"), filter.getJob())); // Remplacez "name" par l'attribut que vous voulez comparer dans l'entité Job
            }
            if (filter.getRegion() != null) {
                Join<User, Region> regionJoin = root.join("regions");
                predicates.add(criteriaBuilder.equal(regionJoin.get("fullName"), filter.getRegion()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}