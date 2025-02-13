package com.cinebook.cinebookback.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilterRequestDTO {
    private String username;
    private String email;
    private String job;
    private String region;
}
