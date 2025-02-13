package com.cinebook.cinebookback.controller;

import com.cinebook.cinebookback.DTO.ProjectDTO;
import com.cinebook.cinebookback.repository.ProjectRepository;
import com.cinebook.cinebookback.service.impl.ProjectService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create")
    public ProjectDTO createProject(@RequestBody ProjectDTO projectDTO, @RequestHeader("Authorization") String authorizationHeader) {
        return projectService.createOrUpdateProject(projectDTO, authorizationHeader);
    }

    @GetMapping("/user/{username}")
    public ProjectDTO findProjectByUsername(@PathVariable String username) {
        return projectService.findProjectByUsername(username);
    }
}