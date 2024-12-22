package com.example.demo.repository;

import com.example.demo.model.Employees;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepo extends MongoRepository<Employees, ObjectId> {
     Optional<Employees> findByEmail(String empEmail);
     void deleteByEmail(String empEmail);
     boolean existsByEmail(String empEmail);
}
