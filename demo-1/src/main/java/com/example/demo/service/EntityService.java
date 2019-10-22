package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Entity;
import com.example.demo.repository.IEntityRepository;

@Service
public class EntityService implements IEntityService {
	@Autowired
	IEntityRepository repo;
	
	
	@Override
	public Entity insertEntity(Entity entity) {
		return (Entity)repo.insert(entity);
	}

	@Override
	public Entity editEntity(Entity entity) {
		// TODO Auto-generated method stub
		return (Entity)repo.save(entity);
	}

	@Override
	public List<Entity> getList() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Entity getById(String id) {
		// TODO Auto-generated method stub
		
		return repo.findById(id).get();
	}

	@Override
	public boolean delete(Entity entity) {
		try {
			repo.delete(entity);
			
		} catch (Exception e) {
			
			return false;
			// TODO: handle exception
		}
		return true;
	}

	@Override
	public List<Entity> getByType(String type) {
		// TODO Auto-generated method stub
		return  repo.findByType(type);
	}
 
	
	
}
