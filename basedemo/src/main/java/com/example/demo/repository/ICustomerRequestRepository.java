package com.example.demo.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CustomerRequest;
import com.example.demo.model.Entity;

@Repository
public interface ICustomerRequestRepository  extends IBaseRepository<CustomerRequest,String>  {

	@Query(value="{isSchedule:false,userId:?0}",count=true)
	int countByUserId(String userId);
	
	@Query(value="{requestDate:{$gte:?0},requestDate:{$lte:?1},userId:?2,isSchedule:false}",count=true)
	int countRequestAllDayPaging(Date fromDate, Date toDate,String userId);
	
	
}
