package com.example.demo.helper;

import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectsHelper {
    private String projectName;
    private String description;
    private String[] techStack;
    private String clientName;
    private Integer budget;
    private boolean isActive;
    private List<ObjectId> empId;
    private LocalDateTime durationStart;
    private LocalDateTime durationEnd;
}
