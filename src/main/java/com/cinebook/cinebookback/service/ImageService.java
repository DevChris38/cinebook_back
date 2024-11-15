package com.cinebook.cinebookback.service;

import com.cinebook.cinebookback.repository.ImageRepository;
import com.cinebook.cinebookback.service.impl.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {
    @Autowired
    ImageRepository imageRepository;

    @Override
    public String getUrl(Integer id) {
        return imageRepository.findLinkById(id);
    }
}
