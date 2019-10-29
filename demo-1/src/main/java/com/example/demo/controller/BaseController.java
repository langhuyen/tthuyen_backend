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
import com.example.demo.model.ResponseModel;
import com.example.demo.repository.GenericDao;
import com.example.demo.service.IBaseService;
import com.example.demo.service.IEntityService;
import com.example.demo.service.IGenerateCodeService;

public class BaseController<T extends BaseMo,String> {
	
	IBaseService<BaseModel,String> service;
	@Autowired 
	
	IGenerateCodeService codeService;
	BaseController(IBaseService<BaseModel,String> service){
		this.service=service;
	}
	
	@GetMapping("/get")
	public ResponseModel get(){
		ResponseModel res=new ResponseModel();
		List<BaseModel> lst=new ArrayList<BaseModel>();
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
		List<BaseModel> lst=new ArrayList<BaseModel>();
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
	public ResponseModel getById(@PathVariable String id){
		ResponseModel res=new ResponseModel();
		List<BaseModel> lst=new ArrayList<BaseModel>();
		try {
			
			BaseModel en=service.getById(id);
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
	@PostMapping("/insert")
	public ResponseModel insert(@RequestBody BaseModel entity){
		ResponseModel res=new ResponseModel();
		List<BaseModel> lst=new ArrayList<BaseModel>();
		try {
//			String codeSys=codeService.getByType(entity.getType().toString());
//			if(entity.getCode().equals(codeSys)) {
//				codeService.editEntity(((BaseModel)entity).getType().toString());
//			};
			BaseModel en=service.insertEntity(entity);
			lst.add(en);
			res.setData(lst);
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	@PutMapping("/entity/update")
	public ResponseModel update(@RequestBody  BaseModel entity){
		ResponseModel res=new ResponseModel();
		List<BaseModel> lst=new ArrayList<BaseModel>();
		try {
			
			BaseModel en=service.editEntity(entity);
			lst.add(en);
			res.setData(lst);
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	@PostMapping("/entity/delete")
	public ResponseModel delete(@RequestBody  BaseModel entity){
		ResponseModel res=new ResponseModel();
		List<Entity> lst=new ArrayList<Entity>();
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
