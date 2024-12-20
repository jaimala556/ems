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
@Document(collection = "department")
public class Departments {
    @Id
    private ObjectId id;
    private String deptName;
    private String description;
    private int maxEmployee;
    private boolean isActive;
    private LocalDateTime lastUpdate;


}
