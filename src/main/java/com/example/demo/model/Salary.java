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
@Document(collection = "salary")
public class Salary {
    @Id
    private ObjectId id;
    private ObjectId empId;
    private Double baseSalary;
    private Double bonus;
private byte totalWorkingDays; //31 or 30 total working days in month
    private byte workingDays; // 21 or 24 work days
    private byte leave;
    private double overtime;
    private String monthYear;
    private Double totalSalary;
    private boolean isPaid;
    private LocalDateTime lastUpdate;

    /*
    how to calculate overtime ?
 .0. ans--  for one hour pay 200 to employee overtime
    * */

}
