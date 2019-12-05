package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomerRequestRefModel;
import com.example.demo.model.Instance;
import com.example.demo.repository.IInstanceRepository;
import com.example.demo.service.TransportService;
//import com.example.demo.ultilities.AutoId;
import com.example.demo.ultilities.AutoId;

import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@RestController
@RequestMapping("/transport")
public class Transport {
	@Autowired
	TransportService service;
	@Autowired
	IInstanceRepository repo;
	
	
	@Autowired
	AutoId autoId;
	@PostMapping("/createdTrip")
	public String createdTrip(@RequestBody List<CustomerRequestRefModel> customerRequestRefModels) throws ParseException {
		service.createdTrip(customerRequestRefModels);
	return "OK";
	}
	
	@GetMapping("/getRouter")
	 public List<Object> updateDb(){
	return	service.getTruck();
	}
		
	@GetMapping("/test")
			
	 public String convertToIdCode(String code) {
		
		return autoId.getNextSequence("huyen");
//		return Integer.parseInt(code.replaceAll("[^0-9]", "")) ;
	}

}
