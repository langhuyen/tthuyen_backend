package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GenerateCode;

@Repository
public interface IGenerateCodeRepository extends MongoRepository<GenerateCode, String> {
	
	GenerateCode findByType(String type);
}
