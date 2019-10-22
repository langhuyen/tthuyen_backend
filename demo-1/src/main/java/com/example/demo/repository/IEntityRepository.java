package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Entity;



@Repository
public interface IEntityRepository extends MongoRepository<Entity, String> {

	List<Entity> findByType(String type);
	
}
