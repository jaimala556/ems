package com.example.demo.repository;

import com.example.demo.model.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepo extends MongoRepository<Users, ObjectId> {
    Optional<Users> findByEmail(String email);
}
