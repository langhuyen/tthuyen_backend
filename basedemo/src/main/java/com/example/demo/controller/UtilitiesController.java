package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Entity;
import com.example.demo.repository.IBaseRepository;

//Ham xử lý tìm kiếm các đối tượng trên db
@RestController
public class UtilitiesController {
	
	@Autowired
	IBaseRepository<Entity, String> repo;
	
}
