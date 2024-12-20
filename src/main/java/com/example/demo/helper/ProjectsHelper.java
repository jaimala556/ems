package com.example.demo.helper;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectsHelper {
    private String projectName;
    private String description;
    private String techStack;
    private String clientName;
    private Integer budget;
    private boolean isActive;
}
