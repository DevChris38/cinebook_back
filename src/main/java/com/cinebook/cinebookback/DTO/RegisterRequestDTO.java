package com.cinebook.cinebookback.DTO;


import lombok.Data;

import java.util.List;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    private List<String> role;
}
