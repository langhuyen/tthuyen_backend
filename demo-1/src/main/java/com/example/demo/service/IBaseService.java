package com.example.demo.service;

import java.io.Serializable;
import java.util.List;

import com.example.demo.model.BaseModel;
import com.example.demo.model.Entity;

public interface IBaseService<BaseModel,String> {
	public BaseModel insertEntity(BaseModel entity);
	public BaseModel editEntity(BaseModel entity);
	public List<BaseModel> getByType(String type);
	public List<BaseModel> getList();
	public BaseModel getById(String id);
	public boolean delete(BaseModel entity);
}
