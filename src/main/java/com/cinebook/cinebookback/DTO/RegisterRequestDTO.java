package com.cinebook.cinebookback.DTO;

import com.cinebook.cinebookback.entity.Job;
import com.cinebook.cinebookback.entity.Region;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String sexe;
    private String phone;
    private String email;
    private String imgProfil;
    private String path;
    private Collection<String> jobs;
    private Collection<String> regions;
    private List<String> role;
}
