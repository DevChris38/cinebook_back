package com.cinebook.cinebookback.repository;

import com.cinebook.cinebookback.entity.Image;
import com.cinebook.cinebookback.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findImageByLink(String link);

    Image findImageByPath(String path);
}
