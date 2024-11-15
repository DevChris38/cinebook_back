package com.cinebook.cinebookback.repository;

import com.cinebook.cinebookback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
