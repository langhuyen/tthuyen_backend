package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Entity;
import com.example.demo.repository.IBaseRepository;
import com.example.demo.service.EntityService;
import com.example.demo.service.IBaseService;


@RestController
@RequestMapping("/entity")
public class EntityController extends BaseController<Entity, String > {

//	@Autowired
//	EntityController(EntityService service) {
//		super(service);
//	}

}
