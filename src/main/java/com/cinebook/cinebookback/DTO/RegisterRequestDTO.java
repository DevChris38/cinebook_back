package com.cinebook.cinebookback.DTO;

import com.cinebook.cinebookback.entity.Job;
import com.cinebook.cinebookback.entity.Region;
import lombok.Data;

import jakarta.validation.constraints.*;

import java.util.Collection;
import java.util.List;

@Data
public class RegisterRequestDTO {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "Firstname is required")
    private String firstname;

    @NotBlank(message = "Lastname is required")
    private String lastname;

    @NotBlank(message = "Sexe is required")
    private String sexe;

    @Pattern(regexp = "^\\+?[0-9]{10,14}$", message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String imgProfil;
    private String path;
    private Collection<String> jobs;
    private Collection<String> regions;
    private List<String> role;
}
