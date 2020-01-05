package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ResponseModel;
import com.example.demo.service.CustomerRequestService;
import com.example.demo.service.InstanceService;
import com.example.demo.service.OverviewService;
import com.example.demo.service.TransportService;
import com.example.demo.ultilities.Auth;

@RestController
public class OverviewController {

	@Autowired
	Auth auth;
	@Autowired 
	InstanceService instanceService;
	@Autowired
	CustomerRequestService requestService;
	@Autowired
	TransportService transportService;
	@Autowired
	OverviewService service;
	@GetMapping("/overview")
	public ResponseModel get(){
		ResponseModel res=new ResponseModel();
		List<Object> lstReq=new ArrayList<Object>();
		List<Object> lstTruck=new ArrayList<Object>();
		try {
			
			lstReq=service.getRequest();
			lstTruck=service.getTruckRouter();
			res.setOther("Request", lstReq);
			res.setOther("Truck", lstTruck);
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	@GetMapping("/overview/statistical")
	public ResponseModel getStatistical(){
		ResponseModel res=new ResponseModel();
		List<Object> lstReq=new ArrayList<Object>();
		List<Object> lstTruck=new ArrayList<Object>();
		try {
			
			int totalContainer=instanceService.countByType("CONTAINER", auth.getAuth("id"));
			int totalMooc=instanceService.countByType("MOOC", auth.getAuth("id"));
			int totalTruck=instanceService.countByType("TRUCK", auth.getAuth("id"));
			int totalRequest=requestService.countByUserId(auth.getAuth("id"));
			int totalTrip=transportService.countByUserId(auth.getAuth("id"));
			
			res.setOther("totalContainer", totalContainer);
			res.setOther("totalMooc", totalMooc);
			res.setOther("totalTruck", totalTruck);
			res.setOther("totalRequest", totalRequest);
			res.setOther("totalTrip", totalTrip);
			
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	
}
