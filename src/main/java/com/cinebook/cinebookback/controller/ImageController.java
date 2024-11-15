package com.cinebook.cinebookback.controller;

import com.cinebook.cinebookback.service.impl.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    IImageService imageService;

    @GetMapping("/url/{id}")
    @ResponseBody
    public String getUrl(@RequestParam Integer id) {
        return imageService.getUrl(id);
    }
}
