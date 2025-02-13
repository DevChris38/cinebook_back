package com.cinebook.cinebookback.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String sexe;
    private String imgProfil;
    private String path;
    private Optional<String> phone;
    private String email;
    private Set<String> jobs = new HashSet<>();
    private Set<String> regions = new HashSet<>();
}
