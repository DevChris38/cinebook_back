package com.cinebook.cinebookback.service.impl;

import com.cinebook.cinebookback.DTO.ProjectDTO;
import com.cinebook.cinebookback.entity.Image;
import com.cinebook.cinebookback.entity.Project;
import com.cinebook.cinebookback.entity.User;
import com.cinebook.cinebookback.mapper.ProjectMapper;
import com.cinebook.cinebookback.repository.ImageRepository;
import com.cinebook.cinebookback.repository.ProjectRepository;
import com.cinebook.cinebookback.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public ProjectDTO createOrUpdateProject(ProjectDTO projectDTO, String authorizationHeader) {
        String username = jwtService.extractUserName(authorizationHeader.substring(7));
        Project project = ProjectMapper.toEntity(projectDTO, projectRepository, imageRepository);
        Project savedProject = projectRepository.save(project);
        User user = this.userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        user.setProject(savedProject);
        userRepository.save(user);
        return ProjectMapper.toDto(savedProject);
    }

    public ProjectDTO findProjectByUsername(String userName) {
        User user = this.userRepository.findByUsername(userName).orElseThrow(EntityNotFoundException::new);
        Project project = user.getProject();
        return project == null ? null : ProjectMapper.toDto(project);
    }

}