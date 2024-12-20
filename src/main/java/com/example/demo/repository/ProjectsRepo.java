package com.example.demo.repository;

import com.example.demo.model.Projects;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectsRepo extends MongoRepository<Projects, ObjectId> {
}
