package com.example.demo.helper;

import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeesHelper {
    private String firstName;
    private String lastName;
    private String email;
    private String departmentName;
    private Double baseSalary;
    private LocalDate joiningDate;
    private ObjectId hireBy;
    private  boolean isActive;
    private List<ObjectId> projectIds;
}
