package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CustomerRequest;
import com.example.demo.model.Entity;


@Repository
public interface ICustomerRequestRepository extends MongoRepository<CustomerRequest, String> {
	List<CustomerRequest> findByType(String type);
	
}
