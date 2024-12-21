package com.example.demo.helper;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentHelper {
    private String deptName;
    private String description;
    private int maxEmployee;
    private boolean isActive;
}
