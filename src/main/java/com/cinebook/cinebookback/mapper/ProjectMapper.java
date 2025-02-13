package com.cinebook.cinebookback.mapper;

import com.cinebook.cinebookback.DTO.ProjectDTO;
import com.cinebook.cinebookback.DTO.UserUpdateDTO;
import com.cinebook.cinebookback.dto.UserDTO;
import com.cinebook.cinebookback.entity.*;
import com.cinebook.cinebookback.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectMapper {

    private final ProjectRepository projectRepository;
    private final ImageRepository imageRepository;

    // Méthode de conversion depuis l'entité Project
    public static ProjectDTO toDto(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .imgPath(project.getImg().getPath())
                .imgLink(project.getImg().getLink())
                .description(project.getDescription())
                .link(project.getLink())
                .type(project.getType())
                .year(project.getYear())
                .build();
    }

    public static Project toEntity(ProjectDTO projectDTO, ProjectRepository projectRepository, ImageRepository imageRepository) {
        Image image = imageRepository.findImageByLink(projectDTO.getImgLink());
        if (image == null) {
            image = imageRepository.save(new Image(projectDTO.getImgPath(), projectDTO.getImgLink()));
        }

        Project project;

        if (projectDTO.getId() == null) {
            project = new Project();
        } else {
            project = projectRepository.findById(projectDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
        }
        project.setName(projectDTO.getName());
        project.setImg(image);
        project.setDescription(projectDTO.getDescription());
        project.setLink(projectDTO.getLink());
        project.setType(projectDTO.getType());
        project.setYear(projectDTO.getYear());

        return project;
    }
}