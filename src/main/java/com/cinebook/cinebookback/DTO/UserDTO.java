package com.cinebook.cinebookback.dto;

import com.cinebook.cinebookback.entity.Job;
import com.cinebook.cinebookback.entity.Region;
import com.cinebook.cinebookback.entity.Role;
import com.cinebook.cinebookback.entity.User;

import com.cinebook.cinebookback.repository.UserRepository;

import lombok.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private String imgProfil;
    private Set<String> roles = new HashSet<>();
    private String firstname;
    private String lastname;
    private String sexe;
    private Optional<String> phone;
    private String email;
    private String inscriptionDate;
    private Boolean isPremium;
    private Set<String> jobs = new HashSet<>();
    private Set<String> regions = new HashSet<>();
}