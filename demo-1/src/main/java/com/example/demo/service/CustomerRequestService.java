package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomerRequest;
import com.example.demo.repository.ICustomerRequestRepository;

@Service
public class CustomerRequestService implements ICustomerRequestService {
//	@Autowired
//	ICustomerRequestRepository repo;
//	@Override
//	public CustomerRequest insertEntity(CustomerRequest entity) {
//		return repo.insert(entity);
//	}
//
//	@Override
//	public CustomerRequest editEntity(CustomerRequest entity) {
//		// TODO Auto-generated method stub
//		return repo.save(entity);
//	}
//
//	@Override
//	public List<CustomerRequest> getList() {
//		// TODO Auto-generated method stub
//		return repo.findAll();
//	}
//
//	@Override
//	public CustomerRequest getById(String id) {
//		// TODO Auto-generated method stub
//		return repo.findById(id).get();
//	}
//
//	@Override
//	public boolean delete(CustomerRequest entity) {
//		// TODO Auto-generated method stub
//		try {
//			repo.delete(entity);
//		} catch (Exception e) {
//			// TODO: handle exception
//			return false;
//		}
//		return true;
//	}
//
//	@Override
//	public List<CustomerRequest> getByType(String type) {
//		// TODO Auto-generated method stub
//		return repo.findByType(type);
//	}

}
