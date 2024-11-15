package com.cinebook.cinebookback.repository;

import com.cinebook.cinebookback.entity.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {

    List<Image> findByName(String name);

    Image findById(long id);

    String findLinkById(long id);
}

