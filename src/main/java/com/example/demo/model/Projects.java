package com.example.demo.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "projects")
public class Projects {
    @Id
    private ObjectId id;
    private String projectName;
    private String description;
    private String techStack;
    private String clientName;
    private Integer budget;
    private boolean isActive;
    private LocalDateTime lastUpdate;

}
