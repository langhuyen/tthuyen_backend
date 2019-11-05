package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


public class EmbedService {

	@Autowired
	MongoTemplate mongoTemplate;
	
	
}
