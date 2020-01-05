package com.example.demo.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.BaseModel;
import com.example.demo.model.Distance;
import com.example.demo.model.Entity;
import com.example.demo.model.Instance;
import com.example.demo.repository.IBaseRepository;


public class BaseService<T extends BaseModel,ID> implements IBaseService<T,ID> {
	Class<T> entityClass;
	
	


	public BaseService() {
//		this.entityClass = entityClass;
	}

	//	public public void setEntityClass(Class<T> entityClass) {
//		this.entityClass = entityClass;
//	}
	@Autowired
	IBaseRepository<T,ID> repo;


	 @Autowired
	    public MongoTemplate mongoTemplate;
	
	
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
	public List<T> getByType(String type,String userID) {
		// TODO Auto-generated method stub
		return  repo.findByType(type,userID);
	}
	
	public T getByCodeID(String type,String key,String userId) {
		// TODO Auto-generated method stub
		return repo.findByIdCode(type, key, userId);
	}

	

	
	@Override
	public List<T> search(String type, String queryString) {
		// TODO Auto-generated method stub
		return repo.searchByField(queryString,type);
	}

	/***
	 *Lấy dữ liệu theo phân trang
	 * 
	 * 
	 *
	 */
	
	@Override
	public List<T> getByTypePaging(String type, String userID, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		int skipSize=(pageIndex-1)*pageSize;
		Query query = new Query();
    	query.addCriteria(Criteria.where("type").is(type).and("userId").is(userID));
    	query.limit(pageSize);
    	query.skip(skipSize);
//    	return mongoTemplate.find(query, Distance.class,"distance");
    	return	mongoTemplate.find(query,entityClass);
	 	
	}

	@Override
	public int countByType(String type, String userId) {
		// TODO Auto-generated method stub
		return repo.countByType(type,userId);
	}

	@Override
	public int countByUserId(String UserId) {
		// TODO Auto-generated method stub
		return repo.countByUserId(UserId);
	}

}
