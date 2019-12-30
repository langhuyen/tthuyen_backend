package com.example.demo.service;

import java.io.Serializable;
import java.util.List;

import com.example.demo.model.BaseModel;
import com.example.demo.model.Entity;

public interface IBaseService<T extends BaseModel,ID> {
	public T insertEntity(T entity);
	public T editEntity(T entity);
	public List<T> getByType(String type,String userID);
	/**
	 * Lấy dữ liệu theo phân trang và theo loại
	 * @param type
	 * @param userID
	 * @return
	 */
	public List<T> getByTypePaging(String type,String userID,int pageIndex,int pageSize);
	public List<T> getList();
	public T getById(ID id);
	public boolean delete(T entity);
	public List<T> search(String type, String queryString);
	public int countByType(String type, String userId);
	public int countByUserId(String UserId);
}
