package com.cinebook.cinebookback.mapper;

import com.cinebook.cinebookback.DTO.UserUpdateDTO;
import com.cinebook.cinebookback.dto.UserDTO;
import com.cinebook.cinebookback.entity.Job;
import com.cinebook.cinebookback.entity.Region;
import com.cinebook.cinebookback.entity.Role;
import com.cinebook.cinebookback.entity.User;
import com.cinebook.cinebookback.repository.JobRepository;
import com.cinebook.cinebookback.repository.RegionRepository;
import com.cinebook.cinebookback.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    // Méthode de conversion depuis l'entité User
    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .imgProfil(user.getImgProfil())
                .roles(user.getRoles().stream()
                        .map(Role::getRoleName) // Transforme les objets Role en noms de rôle
                        .collect(Collectors.toSet()))
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .sexe(user.getSexe())
                .phone(Optional.ofNullable(user.getPhone()))
                .email(user.getEmail())
                .inscriptionDate(user.getInscriptionDate())
                .isPremium(user.getIsPremium())
                .jobs(user.getJobs().stream()
                        .map(Job::getCode)
                        .collect(Collectors.toSet()))
                .regions(user.getRegions().stream()
                        .map(Region::getCode)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static User toEntity(UserDTO userDTO, UserRepository userRepository) {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setImgProfil(userDTO.getImgProfil());

        // Transforme les noms de rôle en objets Role
        user.setRoles(userDTO.getRoles().stream()
                .map(roleName -> {
                    Role role = new Role();
                    role.setRoleName(roleName);
                    return role;
                })
                .collect(Collectors.toSet()));

        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setSexe(userDTO.getSexe());
        user.setPhone(userDTO.getPhone().orElse(null)); // Gestion de l'Optional
        user.setEmail(userDTO.getEmail());
        user.setInscriptionDate(userDTO.getInscriptionDate());
        user.setIsPremium(userDTO.getIsPremium());

        // Transforme les codes des jobs en objets Job
        user.setJobs(userDTO.getJobs().stream()
                .map(jobCode -> {
                    Job job = new Job();
                    job.setCode(jobCode);
                    return job;
                })
                .collect(Collectors.toSet()));

        // Transforme les codes des régions en objets Region
        user.setRegions(userDTO.getRegions().stream()
                .map(regionCode -> {
                    Region region = new Region();
                    region.setCode(regionCode);
                    return region;
                })
                .collect(Collectors.toSet()));

        return user;
    }

    public static User toEntityForUpdate(User user, UserUpdateDTO userUpdateDTO, UserRepository userRepository, JobRepository jobRepository, RegionRepository regionRepository) {
        user.setImgProfil(userUpdateDTO.getImgProfil());

        user.setFirstname(userUpdateDTO.getFirstname());
        user.setLastname(userUpdateDTO.getLastname());
        user.setSexe(userUpdateDTO.getSexe());
        user.setPhone(userUpdateDTO.getPhone().orElse(null)); // Gestion de l'Optional
        user.setEmail(userUpdateDTO.getEmail());

        // Transforme les codes des jobs en objets Job
        Set<Job> jobs = userUpdateDTO.getJobs().stream()
                .map(jobCode -> jobRepository.findByCode(jobCode)
                        .orElseThrow(() -> new IllegalArgumentException("Job not found with code: " + jobCode)))
                .collect(Collectors.toSet());
        user.setJobs(jobs);

        // Transforme les codes des régions en objets Region
        Set<Region> regions = userUpdateDTO.getRegions().stream()
                .map(regionCode -> regionRepository.findByCode(regionCode)
                        .orElseThrow(() -> new IllegalArgumentException("Region not found with code: " + regionCode)))
                .collect(Collectors.toSet());
        user.setRegions(regions);

        return user;
    }
}