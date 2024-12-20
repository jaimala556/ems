package com.example.demo.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection ="employees")
public class Employees {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String email;
    private String departmentName;
    private Double baseSalary; //base salary for 1 day =500 ruppess
     private LocalDate joiningDate;
    private ObjectId hireBy;
    private  boolean isActive;
    private List<ObjectId> projectIds;
    private List<ObjectId> salaryIds;
    private LocalDateTime lastUpdate;
}
