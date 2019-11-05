package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomerRequest;
import com.example.demo.model.CustomerRequestRefModel;
import com.example.demo.model.Entity;
import com.example.demo.repository.IBaseRepository;
import com.example.demo.repository.ICustomerRequestRepository;
import com.mongodb.client.model.Aggregates;

@Service
public class CustomerRequestService extends BaseService<CustomerRequest>{

	/**
	 * Thực hiện lấy các request chứa các loại
	 */

	 @Autowired
	    private MongoTemplate mongoTemplate;

	 
	 public List<CustomerRequestRefModel> getByTypeList(String type){
		
		 
		 
	    LookupOperation lookupOperation = LookupOperation.newLookup()
	                        .from("entity")
	                        .localField("warehouseCode")
	                        .foreignField("_id")
	                        .as("warehouseCode");
	    LookupOperation lookupPortCode = LookupOperation.newLookup()
	    		.from("entity")
	    		.localField("portCode")
	    		.foreignField("_id")
	    		.as("portCode");
	    LookupOperation lookupContainer = LookupOperation.newLookup()
	    		.from("instance")
	    		.localField("containerCode")
	    		.foreignField("_id")
	    		.as("containerCode");
	    

	    Aggregation aggregation = Aggregation.newAggregation(
	    		new AggregationOperation() {
					
					@Override
					public Document toDocument(AggregationOperationContext context) {
						// TODO Auto-generated method stub
		
						  return new Document("$addFields",new Document("warehouseCode",new Document("$toObjectId","$warehouseCode")));
					}
				},
	    		new AggregationOperation() {
					
					@Override
					public Document toDocument(AggregationOperationContext context) {
						// TODO Auto-generated method stub
						
						return new Document("$addFields",new Document("portCode",new Document("$toObjectId","$portCode")));
					}
				},
	    		new AggregationOperation() {
					
					@Override
					public Document toDocument(AggregationOperationContext context) {
						// TODO Auto-generated method stub
						
						return new Document("$addFields",new Document("containerCode",new Document("$toObjectId","$containerCode")));
					}
				},
	    		Aggregation.match(Criteria.where("type").is(type)) ,
	    		lookupOperation,
	    		lookupPortCode,
	    		lookupContainer);
	        List<CustomerRequestRefModel> customerRequestRefModels = mongoTemplate.aggregate(aggregation, "customer_request", CustomerRequestRefModel.class).getMappedResults();
	        return customerRequestRefModels;
	    }
	 /***
	  * Tthuyen
	  * lấy danh sách các request trong 1 ngày
	  * @param type
	  * @return
	  */
	 public List<CustomerRequestRefModel> getListRequestAllDay(Date date){
			
		 	//Sử lý Date
		 	@Deprecated
		 	Date dateStart=new Date(date.getYear(), date.getMonth(), date.getDate(), 0, 0, 0);
		 	@Deprecated
		 	Date dateEnd=new Date(date.getYear(), date.getMonth(), date.getDate(), 23, 59, 59);
		 
		    LookupOperation lookupOperation = LookupOperation.newLookup()
		                        .from("entity")
		                        .localField("warehouseCode")
		                        .foreignField("_id")
		                        .as("warehouseCode");
		    LookupOperation lookupPortCode = LookupOperation.newLookup()
		    		.from("entity")
		    		.localField("portCode")
		    		.foreignField("_id")
		    		.as("portCode");
		    LookupOperation lookupContainer = LookupOperation.newLookup()
		    		.from("instance")
		    		.localField("containerCode")
		    		.foreignField("_id")
		    		.as("containerCode");
		    

		    Aggregation aggregation = Aggregation.newAggregation(
		    		new AggregationOperation() {
						
						@Override
						public Document toDocument(AggregationOperationContext context) {
							// TODO Auto-generated method stub
			
							  return new Document("$addFields",new Document("warehouseCode",new Document("$toObjectId","$warehouseCode")));
						}
					},
		    		new AggregationOperation() {
						
						@Override
						public Document toDocument(AggregationOperationContext context) {
							// TODO Auto-generated method stub
							
							return new Document("$addFields",new Document("portCode",new Document("$toObjectId","$portCode")));
						}
					},
		    		new AggregationOperation() {
						
						@Override
						public Document toDocument(AggregationOperationContext context) {
							// TODO Auto-generated method stub
							
							return new Document("$addFields",new Document("containerCode",new Document("$toObjectId","$containerCode")));
						}
					},
		    		Aggregation.match(Criteria.where("requestDate").lte(dateEnd).gte(dateStart)) ,
		    		lookupOperation,
		    		lookupPortCode,
		    		lookupContainer);
		        List<CustomerRequestRefModel> customerRequestRefModels = mongoTemplate.aggregate(aggregation, "customer_request", CustomerRequestRefModel.class).getMappedResults();
		        return customerRequestRefModels;
		    }
}
