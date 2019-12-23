package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserModel;

@Repository
public interface IUserRepository extends MongoRepository<UserModel, String> {
 UserModel findByEmail(String email);
 Boolean existsByUsername(String username);
 Boolean existsByEmail(String email);
UserModel findByUsername(String username);
}
