package com.example.demo.repository;

import com.example.demo.model.Salary;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalaryRepo extends MongoRepository<Salary, ObjectId> {
}
