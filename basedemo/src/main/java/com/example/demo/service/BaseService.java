package com.example.demo.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.BaseModel;
import com.example.demo.model.Entity;
import com.example.demo.model.Instance;
import com.example.demo.repository.IBaseRepository;


public class BaseService<T extends BaseModel,ID> implements IBaseService<T,ID> {
	@Autowired
	IBaseRepository<T,ID> repo;

	
	@Override
	public T insertEntity(T entity) {
		return repo.insert(entity);
	}

	@Override
	public T editEntity(T entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
	}

	@Override
	public List<T> getList() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public T getById(ID id) {
		// TODO Auto-generated method stub
		
		return repo.findById(id).get();
	}

	@Override
	public boolean delete(T entity) {
		try {
			repo.delete(entity);
			
		} catch (Exception e) {
			
			return false;
			// TODO: handle exception
		}
		return true;
	}

	@Override
	public List<T> getByType(String type) {
		// TODO Auto-generated method stub
		return  repo.findByType(type);
	}
	
	public T getByCodeID(String key) {
		// TODO Auto-generated method stub
		return repo.findByIdCode(key);
	}

	


	@Override
	public List<T> search(String type, String queryString) {
		// TODO Auto-generated method stub
		return repo.searchByField(queryString,type);
	}
}
