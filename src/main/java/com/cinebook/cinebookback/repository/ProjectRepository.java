package com.cinebook.cinebookback.repository;

import com.cinebook.cinebookback.entity.Job;
import com.cinebook.cinebookback.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findById(Long id);
}
