package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomerRequestRefModel;
import com.example.demo.model.Instance;

@Service
public class InstanceService extends BaseService<Instance,String>{

	/***
	 * Lấy các thực thể rảnh của 1 loại
	 */

	 @Autowired
	    private MongoTemplate mongoTemplate;
	
	public  List<Instance> getInstanceFreeByType(){
		  Aggregation aggregation = Aggregation.newAggregation(
				  Aggregation.match(Criteria.where("type").ne("CONTAINER")),
				  Aggregation.match(Criteria.where("status").is("Rảnh"))
				  );
		return mongoTemplate.aggregate(aggregation, "instance", Instance.class).getMappedResults();
	}
}
