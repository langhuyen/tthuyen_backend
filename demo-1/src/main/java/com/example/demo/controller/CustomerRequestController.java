package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomerRequest;
import com.example.demo.model.ResponseModel;
import com.example.demo.service.ICustomerRequestService;
import com.example.demo.service.IGenerateCodeService;

@RestController
public class CustomerRequestController {

	@Autowired
	ICustomerRequestService service;
	@Autowired
	IGenerateCodeService codeService;
	
	@GetMapping("/CustomerRequest/get")
	public ResponseModel get(){
		ResponseModel res=new ResponseModel();
		List<CustomerRequest> lst=new ArrayList<CustomerRequest>();
		try {
			
			lst=service.getList();
			if(lst.size()==0) {
				res.notFound();
			}else {
				
				res.setData(lst);
			}
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	@GetMapping("/CustomerRequest/getType/:{type}")
	public ResponseModel getByType(@PathVariable String type){
		ResponseModel res=new ResponseModel();
		List<CustomerRequest> lst=new ArrayList<CustomerRequest>();
		try {
			
			lst=service.getByType(type);
			if(lst.size()==0) {
				res.notFound();
			}else {
				
				res.setData(lst);
			}
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	@GetMapping("/CustomerRequest/detail/:{id}")
	public ResponseModel getById(@PathVariable String id){
		ResponseModel res=new ResponseModel();
		List<CustomerRequest> lst=new ArrayList<CustomerRequest>();
		try {
			
			CustomerRequest en=service.getById(id);
			lst.add(en);
			if(lst.size()==0) {
				res.notFound();
			}else {
				
				res.setData(lst);
			}
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	@PostMapping("/CustomerRequest/insert")
	public ResponseModel insert(@RequestBody  CustomerRequest CustomerRequest){
		ResponseModel res=new ResponseModel();
		List<CustomerRequest> lst=new ArrayList<CustomerRequest>();
		try {
			String codeSys=codeService.getByType(CustomerRequest.getType().toString());
			if(CustomerRequest.getCode().equals(codeSys)) {
				codeService.editEntity(CustomerRequest.getType().toString());
			};
			CustomerRequest en=service.insertEntity(CustomerRequest);
			lst.add(en);
			res.setData(lst);
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	@PutMapping("/CustomerRequest/update")
	public ResponseModel update(@RequestBody CustomerRequest CustomerRequest){
		ResponseModel res=new ResponseModel();
		List<CustomerRequest> lst=new ArrayList<CustomerRequest>();
		try {
			
			CustomerRequest en=service.editEntity(CustomerRequest);
			lst.add(en);
			res.setData(lst);
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	@PostMapping("/CustomerRequest/delete")
	public ResponseModel delete(@RequestBody CustomerRequest CustomerRequest){
		ResponseModel res=new ResponseModel();
		List<CustomerRequest> lst=new ArrayList<CustomerRequest>();
		try {
			
			boolean  result=service.delete(CustomerRequest);
		
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
}
