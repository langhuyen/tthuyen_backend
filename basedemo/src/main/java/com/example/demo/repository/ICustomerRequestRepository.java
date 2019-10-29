package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.CustomerRequest;
import com.example.demo.model.Entity;

@Repository
public interface ICustomerRequestRepository  extends IBaseRepository<CustomerRequest>  {

}
