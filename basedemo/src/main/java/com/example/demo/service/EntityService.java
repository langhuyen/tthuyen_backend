package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.example.demo.model.Entity;
import com.example.demo.model.Instance;
import com.example.demo.repository.IBaseRepository;
import com.example.demo.repository.IEntityRepository;

@Service
public class EntityService extends BaseService<Entity> {

	/**
	 * Lay danh sach cac depot không phải port và warehouse
	 */
	 @Autowired
	    private MongoTemplate mongoTemplate;

	public List<Entity> getEntityMiddle(){
		  Aggregation aggregation = Aggregation.newAggregation(
				  Aggregation.match(Criteria.where("type").ne("PORT")),
				  Aggregation.match(Criteria.where("type").ne("WAREHOUSE"))
				  );
		return mongoTemplate.aggregate(aggregation, "entity", Entity.class).getMappedResults();
	}
}
