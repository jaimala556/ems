package com.example.demo.repository;

import com.example.demo.model.Departments;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepo extends MongoRepository<Departments, ObjectId> {
  Optional<Departments> findByDeptName(String deptName);
  void deleteByDeptName(String deptName);
  boolean existsByDeptName(String deptName);
}
