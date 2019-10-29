package com.example.demo.service;

import java.io.Serializable;
import java.util.List;

import com.example.demo.model.BaseModel;
import com.example.demo.model.Entity;

public interface IBaseService<T extends BaseModel> {
	public T insertEntity(T entity);
	public T editEntity(T entity);
	public List<T> getByType(String type);
	public List<T> getList();
	public T getById(String id);
	public boolean delete(T entity);
}
