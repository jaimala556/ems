package com.example.demo.helper;

import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalaryHelper {
    private ObjectId empId;
    private Double bonus;
    private byte totalWorkingDays;
    private byte workingDays;
    private byte leave;
    private byte overtime;
    private String monthYear;
    private Double totalSalary;
    private boolean isPaid;
}
