package com.cinebook.cinebookback.repository;

import com.cinebook.cinebookback.entity.Job;
import com.cinebook.cinebookback.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByCode(String code);
}
