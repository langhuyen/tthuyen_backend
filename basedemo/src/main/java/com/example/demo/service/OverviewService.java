package com.example.demo.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.stereotype.Service;

import net.minidev.json.JSONObject;

@Service
public class OverviewService {

	@Autowired
	MongoTemplate mongoTemplate;
	public List<Object> getTruckRouter(){
		
		
		AggregationOperation ac = new AggregationOperation() {

			@Override
			public Document toDocument(AggregationOperationContext context) {
				// TODO Auto-generated method stub
				JSONObject obj = new JSONObject();
				obj.put("_id", "$createdDate");
				obj.put("count", new Document("$sum", 1));
				return new Document("$group", new Document(obj));
			}
		};
		Aggregation aggregation = Aggregation.newAggregation(ac);
		return  mongoTemplate.aggregate(aggregation, "truck_router", Object.class).getMappedResults();
	}
	public List<Object> getRequest(){
		
		
		AggregationOperation group = new AggregationOperation() {

			@Override
			public Document toDocument(AggregationOperationContext context) {
				// TODO Auto-generated method stub
				JSONObject obj = new JSONObject();
				obj.put("_id", "$requestDate");
				obj.put("count", new Document("$sum", 1));
				
				return new Document("$group", new Document(obj));
			}
		};
		Aggregation aggregation = Aggregation.newAggregation(group);
		return mongoTemplate.aggregate(aggregation, "customer_request", Object.class).getMappedResults();
	}
	
}
