package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BaseModel;
import com.example.demo.model.CustomerRequest;

@Repository
public interface GenericDao<BaseModel,String> extends MongoRepository<BaseModel, String>{
 List<BaseModel> findByType(String type);
}
