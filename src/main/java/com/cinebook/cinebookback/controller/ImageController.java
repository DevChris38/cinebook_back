package com.cinebook.cinebookback.controller;

import com.cinebook.cinebookback.service.impl.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cinebook.cinebookback.dto.UserDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @DeleteMapping("/{path}")
    public ResponseEntity<Map<String, String>> deleteImage(@PathVariable String path) {
        imageService.deleteImage(path);
        Map<String, String> response = new HashMap<>();
        response.put("path", path);
        return ResponseEntity.ok(response);
    }
}
