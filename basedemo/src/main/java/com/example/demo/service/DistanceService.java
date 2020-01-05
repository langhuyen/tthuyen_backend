package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.Distance;
import com.example.demo.model.Instance;
import com.example.demo.repository.IDistanceRepository;

@Service
public class DistanceService extends BaseService<Distance,String>{
	
	public DistanceService() {
		this.entityClass=Distance.class;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Tim cac doi tuong co srcCode=srcCode và có desCode=desCode
	 * @param srcCode
	 * @return
	 */
	 @Autowired
	    private MongoTemplate mongoTemplate;

        public List<Distance> findBySrcCode(Integer srcCode,Integer desCode){
        	Query query = new Query();
        	query.addCriteria(Criteria.where("srcCode").is(srcCode).and("desCode").is(desCode));
        	return mongoTemplate.find(query, Distance.class,"distance");
        	
        }
        
        public void deleteBySrc(Integer locationCode) {
        	Query query = new Query();
        	query.addCriteria(
        		    new Criteria().orOperator(
        		        Criteria.where("srcCode").is(locationCode),
        		        Criteria.where("desCode").is(locationCode)
        		    )
        		);
        	 mongoTemplate.remove(query, Distance.class);
        }
}
