package com.cinebook.cinebookback.repository;

import com.cinebook.cinebookback.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByCode(String code);
}
