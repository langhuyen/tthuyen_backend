package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BaseModel;

//@Repository
public interface  IBaseRepository<T extends BaseModel> extends MongoRepository<T,String>{
	
	 List<T> findByType(String type);
}
