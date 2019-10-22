package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Entity;

public interface IEntityService {

	
	public Entity insertEntity(Entity entity);
	public Entity editEntity(Entity entity);
	public List<Entity> getByType(String type);
	public List<Entity> getList();
	public Entity getById(String id);
	public boolean delete(Entity entity);
}
