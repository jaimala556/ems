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
@Document(collection="users")
public class Users {
    @Id
    private ObjectId id;
    private String email;
    private String password;
    private String firstName;
    private LocalDateTime lastUpdate;
    private int otp;
    private boolean isActive;
}
