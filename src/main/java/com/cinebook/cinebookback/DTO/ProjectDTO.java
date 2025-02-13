package com.cinebook.cinebookback.DTO;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDTO {
    private Long id;
    private String name;
    private String imgPath;
    private String imgLink;
    private String description;
    private String link;
    private String type;
    private Integer year;
}