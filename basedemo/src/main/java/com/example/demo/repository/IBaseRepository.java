package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BaseModel;
import com.example.demo.model.Instance;

//@Repository
public interface  IBaseRepository<T extends BaseModel,ID> extends MongoRepository<T,ID>{
	
	 List<T> findByType(String type);

	T findByCode(String key);

	T findByIdCode(String key);
}
