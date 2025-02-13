package com.cinebook.cinebookback.service.impl;

import com.cinebook.cinebookback.entity.Image;
import com.cinebook.cinebookback.entity.User;
import com.cinebook.cinebookback.mapper.UserMapper;
import com.cinebook.cinebookback.repository.ImageRepository;
import com.cinebook.cinebookback.repository.JobRepository;
import com.cinebook.cinebookback.repository.RegionRepository;
import com.cinebook.cinebookback.repository.UserRepository;
import com.cinebook.cinebookback.service.impl.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public String deleteImage(String path) {
        Image imageToDelete = imageRepository.findImageByPath(path);
        imageRepository.deleteById(imageToDelete.getId());
        return imageToDelete.getPath();
    }
}