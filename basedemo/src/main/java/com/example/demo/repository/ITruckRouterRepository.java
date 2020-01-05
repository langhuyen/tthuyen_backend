package com.example.demo.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.TruckRoute;

public interface ITruckRouterRepository extends MongoRepository<TruckRoute, String> {
	@Query(value="{createdDate:{$gte:?0},createdDate:{$lte:?1},userId:?2}",count=true)
	int countByUserIdByTime(Date fromDate, Date toDate,String userId);
	int countByUserId(String userId);
}
