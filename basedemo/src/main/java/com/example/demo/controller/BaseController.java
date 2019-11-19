package com.example.demo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.BaseModel;
import com.example.demo.model.Entity;
import com.example.demo.model.GenerateCode;
import com.example.demo.model.ResponseModel;
import com.example.demo.repository.IBaseRepository;
import com.example.demo.service.BaseService;
import com.example.demo.service.GenerateCodeService;
import com.example.demo.service.IBaseService;


public class BaseController<T extends BaseModel,ID extends Serializable> {
	
	@Autowired
	IBaseService<T,ID> service;

	@Autowired 
	IBaseService<GenerateCode,String> codeService;

	
	@GetMapping("/get")
	public ResponseModel get(){
		ResponseModel res=new ResponseModel();
		List<T> lst=new ArrayList<T>();
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
	@GetMapping("/getType/:{type}")
	public ResponseModel getByType(@PathVariable String type){
		ResponseModel res=new ResponseModel();
		List<T> lst=new ArrayList<T>();
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
	@GetMapping("/detail/:{id}")
	public ResponseModel getById(@PathVariable ID id){
		ResponseModel res=new ResponseModel();
		List<T> lst=new ArrayList<T>();
		try {
			
			T en=service.getById(id);
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
	
	/**
	 * Thực hiện insert 
	 * @param entity
	 * @return
	 */
	@PostMapping("/insert")
	public ResponseModel insert(@RequestBody T entity){
		ResponseModel res=new ResponseModel();
		List<T> lst=new ArrayList<T>();
		try {
			String codeSys=((GenerateCodeService)codeService).getByOtherType(entity.getType().toString());
			if(entity.getCode().equals(codeSys)) {
				((GenerateCodeService)codeService).editType(entity.getType().toString());
				};
				GenerateCode idCode=((GenerateCodeService)codeService).getIdCodeByType(entity.getType().toString());
				entity.setIdCode(idCode.getIdCode());
				idCode.setIdCode(idCode.getIdCode()+1);
				((GenerateCodeService)codeService).editEntity(idCode);
			T en=service.insertEntity(entity);
			lst.add(en);
			res.setData(lst);
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	@PutMapping("/update")
	public ResponseModel update(@RequestBody  T entity){
		ResponseModel res=new ResponseModel();
		List<T> lst=new ArrayList<T>();
		try {
			
			T en=service.editEntity(entity);
			lst.add(en);
			res.setData(lst);
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	@PostMapping("/delete")
	public ResponseModel delete(@RequestBody  T entity){
		ResponseModel res=new ResponseModel();
		List<T> lst=new ArrayList<T>();
		try {
			
			boolean  result=service.delete(entity);
		
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}

}
