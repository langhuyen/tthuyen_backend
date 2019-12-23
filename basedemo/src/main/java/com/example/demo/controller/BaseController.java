package com.example.demo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import com.example.demo.ultilities.Auth;
import com.example.demo.ultilities.AutoId;


public class BaseController<T extends BaseModel,ID extends Serializable> {
	
	@Autowired
	IBaseService<T,ID> service;

	@Autowired 
	IBaseService<GenerateCode,String> codeService;
	
	@Autowired
	Auth auth;

	
	@Autowired
	AutoId autoId;
	
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
//	//Phân trang
//	@GetMapping("/getType/:{type}")
//	public ResponseModel getByType(@PathVariable String type){
//		ResponseModel res=new ResponseModel();
//		List<T> lst=new ArrayList<T>();
//		try {
//			
//			lst=service.getByType(type);
//			if(lst.size()==0) {
//				res.notFound();
//			}else {
//				
//				res.setData(lst);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			res.error();
//			res.setMessage(e.toString());
//		}
//		return res;
//	}
	@GetMapping("/detail")
	public ResponseModel getById(@Param(value="id") ID id){
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
			String userId=auth.getAuth("id");
			entity.setUseId(userId);
			String codeSys=((GenerateCodeService)codeService).getByOtherType(entity.getType().toString());
			if(entity.getCode().equals(codeSys)) {
				((GenerateCodeService)codeService).editType(entity.getType().toString());
				};
				GenerateCode idCode=((GenerateCodeService)codeService).getIdCodeByType(entity.getType().toString());
				entity.setIdCode(autoId.getNextSequence("auto_id"));
				entity.setUseId(auth.getAuth("id"));
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

	/**
	 * Hàm thực hiện fulltextsearch'
	 * 
	 */
	@GetMapping("/search")
	public ResponseModel search(@Param(value="type") String type, @Param(value="queryString") String queryString) {
		ResponseModel res=new ResponseModel();
		List<T> lst=new ArrayList<T>();
		try {
			
			lst=service.search(type, queryString);
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
	
}
