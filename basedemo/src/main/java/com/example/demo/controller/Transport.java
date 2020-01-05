package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomerRequestRefModel;
import com.example.demo.model.DateParam;
import com.example.demo.model.Instance;
import com.example.demo.model.ResponseModel;
import com.example.demo.repository.IInstanceRepository;
import com.example.demo.service.CustomerRequestService;
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
	public boolean createdTrip(@RequestBody List<CustomerRequestRefModel> customerRequestRefModels) throws ParseException {
		boolean result=false;
		try {
			
		result=	service.createdTrip(customerRequestRefModels);
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	return result;
	}
	
	@GetMapping("/getRouter")
	 public List<Object> getRouter(){
	return	service.getTruck();
	}
	
	@PostMapping("/getRouterPaging")
	 public ResponseModel getRouterPaging(@RequestBody DateParam dateParam, @Param(value="pageIndex") int pageIndex,@Param(value="pageSize") int pageSize){
		
		ResponseModel res=new ResponseModel();
		List<Object> lst=new ArrayList<Object>();
		try {
			
			lst=service.getTruckPaging(dateParam.getFromDate(),dateParam.getToDate(), pageIndex, pageSize);;
			int totalPage=service.countByUserIdByTime(dateParam.getFromDate(),dateParam.getToDate());
			if(lst.size()==0) {
				res.setData(lst);
				res.setOther("totalPage", 0);
			}else {
				
				res.setData(lst);
				res.setOther("totalPage", totalPage);
			}
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
		
		
	
	}
	
	
		
	@GetMapping("/test")
			
	 public String convertToIdCode(String code) {
		
		return autoId.getNextSequence("huyen");
//		return Integer.parseInt(code.replaceAll("[^0-9]", "")) ;
	}

}
