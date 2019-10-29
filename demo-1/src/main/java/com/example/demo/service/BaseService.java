package com.example.demo.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BaseModel;
import com.example.demo.model.Entity;
import com.example.demo.repository.GenericDao;
import com.example.demo.repository.IEntityRepository;

@Service
public class BaseService<BaseModel,String> implements IBaseService<BaseModel, String> {

	@Autowired
	GenericDao<BaseModel, String> repo;
	
	
	@Override
	public BaseModel insertEntity(BaseModel entity) {
		return (BaseModel)repo.insert(entity);
	}

	@Override
	public BaseModel editEntity(BaseModel entity) {
		// TODO Auto-generated method stub
		return (BaseModel)repo.save(entity);
	}

	@Override
	public List<BaseModel> getList() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public BaseModel getById(String id) {
		// TODO Auto-generated method stub
		
		return repo.findById(id).get();
	}

	@Override
	public boolean delete(BaseModel entity) {
		try {
			repo.delete(entity);
			
		} catch (Exception e) {
			
			return false;
			// TODO: handle exception
		}
		return true;
	}

	@Override
	public List<BaseModel> getByType(String type) {
		// TODO Auto-generated method stub
		return  repo.findByType(type);
	}
}
