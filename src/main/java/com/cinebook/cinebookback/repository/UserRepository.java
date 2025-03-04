package com.cinebook.cinebookback.repository;

import com.cinebook.cinebookback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String username);

}