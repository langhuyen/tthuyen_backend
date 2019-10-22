package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CustomerRequest;
import com.example.demo.model.Entity;

public interface ICustomerRequestService {
	public CustomerRequest insertEntity(CustomerRequest entity);
	public CustomerRequest editEntity(CustomerRequest entity);
	public List<CustomerRequest> getList();
	public List<CustomerRequest> getByType(String type);
	public CustomerRequest getById(String id);
	public boolean delete(CustomerRequest entity);
}
