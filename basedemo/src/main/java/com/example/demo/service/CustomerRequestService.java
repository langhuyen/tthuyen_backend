package com.example.demo.service;

import java.util.Date;
import java.util.List;

import javax.validation.valueextraction.Unwrapping.Skip;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomerRequest;
import com.example.demo.model.CustomerRequestRefModel;
import com.example.demo.model.DateParam;
import com.example.demo.model.Entity;
import com.example.demo.repository.IBaseRepository;
import com.example.demo.repository.ICustomerRequestRepository;
import com.example.demo.ultilities.Auth;
import com.mongodb.client.model.Aggregates;

@Service
public class CustomerRequestService extends BaseService<CustomerRequest,String>{
	
	@Autowired
	Auth auth;
	public CustomerRequestService() {
//		super(CustomerRequest.class);
		this.entityClass=CustomerRequest.class;
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Thực hiện lấy các request chứa các loại
	 */

//		@Autowired
//	   MongoTemplate mongoTemplate;

	 
	 public boolean updateStatusIsSchedule(String id,Boolean isSchedule) {
		 CustomerRequest req=repo.findById(id).get();
		 if(req!=null) {
			 try {
				 req.setIsSchedule(isSchedule);
				repo.save(req);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			 return true;
		 }
		 return false;
		 
	 }
	 
	 
	 /**
	  * Lấy danh sach các customerrequest phục vụ cho việc lập lịch
	  * @param type
	  * @return
	  */
	 
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
	  * Thực hiện Lấy danh sách pagging
	  * @param type
	  * @return
	  */
	 public List<CustomerRequestRefModel> getByTypeListPaging(String type,int pageIndex,int pageSize){
		
		 
		 
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
	    LimitOperation limit=new LimitOperation(pageSize);
	    SkipOperation offset=new SkipOperation((pageIndex-1)*pageSize);
	    

	    @SuppressWarnings("deprecation")
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
	    		lookupContainer,
	    		Aggregation.skip((pageIndex-1)*pageSize),
	    		Aggregation.limit(pageSize));
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
		    		Aggregation.match(Criteria.where("requestDate").lte(dateEnd).gte(dateStart).and("isSchedule").is(false)) ,
		    		lookupOperation,
		    		lookupPortCode,
		    		lookupContainer);
		        List<CustomerRequestRefModel> customerRequestRefModels = mongoTemplate.aggregate(aggregation, "customer_request", CustomerRequestRefModel.class).getMappedResults();
		        return customerRequestRefModels;
		    }
	
	 /***
	  * lấy dữ liệu theo paging
	  * @param date
	  * @return
	  */
	 public List<CustomerRequestRefModel> getListRequestAllDayPaging(DateParam date,int pageIndex,int pageSize){
		 
		 //Sử lý Date
		 @Deprecated
		 Date dateStart=new Date(date.getFromDate().getYear(), date.getFromDate().getMonth(), date.getFromDate().getDate(), 0, 0, 0);
		 @Deprecated
		 Date dateEnd=new Date(date.getToDate().getYear(), date.getToDate().getMonth(), date.getToDate().getDate(), 23, 59, 59);
		 
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
		 LimitOperation limit=new LimitOperation(pageSize);
		 SkipOperation offset=new SkipOperation((pageIndex-1)*pageSize);
		 
		 @SuppressWarnings("deprecation")
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
				 Aggregation.match(Criteria.where("requestDate").lte(dateEnd).gte(dateStart).and("isSchedule").is(false).and("userId").is(auth.getAuth("id"))) ,
				 lookupOperation,
				 lookupPortCode,
				 lookupContainer,
				 Aggregation.skip((pageIndex-1)*pageSize),
				 Aggregation.limit(pageSize));
		 List<CustomerRequestRefModel> customerRequestRefModels = mongoTemplate.aggregate(aggregation, "customer_request", CustomerRequestRefModel.class).getMappedResults();
		 return customerRequestRefModels;
	 }
	 
	 public int countRequestAllDayPaging(DateParam date) {
		 @Deprecated
		 Date dateStart=new Date(date.getFromDate().getYear(), date.getFromDate().getMonth(), date.getFromDate().getDate(), 0, 0, 0);
		 @Deprecated
		 Date dateEnd=new Date(date.getToDate().getYear(), date.getToDate().getMonth(), date.getToDate().getDate(), 23, 59, 59);
		
		return ((ICustomerRequestRepository)repo).countRequestAllDayPaging(dateStart, dateEnd, auth.getAuth("id")); 
	 }
}
