package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BaseModel;
import com.example.demo.model.Instance;

//@Repository
public interface  IBaseRepository<T extends BaseModel,ID> extends MongoRepository<T,ID>{
	
	@Query("{type:?0,userId:?1}")
	 List<T> findByType(String type,String id);
	
	@Query("{type:?0,userId:?1}")
	T findByCode(String key);
	@Query("{type:?0,idCode:?1}")
	T findByIdCode(String type,String key);
	
	@Query("{$text:{$search:\"\\\"?0\\\"\",$caseSensitive:false},type:\"?1\"}")
	List<T> searchByField(String queryString,String type);
	@Query(value="{type:?0,userId:?1}",count=true)
	int countByType(String type, String userId);
	int countByUserId(String userId);
}
