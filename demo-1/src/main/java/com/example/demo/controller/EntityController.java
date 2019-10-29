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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Entity;
import com.example.demo.model.GenerateCode;
import com.example.demo.model.ResponseModel;
import com.example.demo.service.IEntityService;
import com.example.demo.service.IGenerateCodeService;


@RestController
@RequestMapping("/entity")
public class EntityController {
//	@Autowired
//	IEntityService service;
//	@Autowired
//	IGenerateCodeService codeService;
//	
//	@GetMapping("/entity/get")
//	public ResponseModel get(){
//		ResponseModel res=new ResponseModel();
//		List<Entity> lst=new ArrayList<Entity>();
//		try {
//			
//			lst=service.getList();
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
//	@GetMapping("/entity/getType/:{type}")
//	public ResponseModel getByType(@PathVariable String type){
//		ResponseModel res=new ResponseModel();
//		List<Entity> lst=new ArrayList<Entity>();
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
//	@GetMapping("/entity/detail/:{id}")
//	public ResponseModel getById(@PathVariable String id){
//		ResponseModel res=new ResponseModel();
//		List<Entity> lst=new ArrayList<Entity>();
//		try {
//			
//			Entity en=service.getById(id);
//			lst.add(en);
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
//	@PostMapping("/entity/insert")
//	public ResponseModel insert(@RequestBody Entity entity){
//		ResponseModel res=new ResponseModel();
//		List<Entity> lst=new ArrayList<Entity>();
//		try {
//			String codeSys=codeService.getByType(entity.getType().toString());
//			if(entity.getCode().equals(codeSys)) {
//				codeService.editEntity(entity.getType().toString());
//			};
//			Entity en=service.insertEntity(entity);
//			lst.add(en);
//			res.setData(lst);
//		} catch (Exception e) {
//			// TODO: handle exception
//			res.error();
//			res.setMessage(e.toString());
//		}
//		return res;
//	}
//	@PutMapping("/entity/update")
//	public ResponseModel update(@RequestBody  Entity entity){
//		ResponseModel res=new ResponseModel();
//		List<Entity> lst=new ArrayList<Entity>();
//		try {
//			
//			Entity en=service.editEntity(entity);
//			lst.add(en);
//			res.setData(lst);
//		} catch (Exception e) {
//			// TODO: handle exception
//			res.error();
//			res.setMessage(e.toString());
//		}
//		return res;
//	}
//	@PostMapping("/entity/delete")
//	public ResponseModel delete(@RequestBody  Entity entity){
//		ResponseModel res=new ResponseModel();
//		List<Entity> lst=new ArrayList<Entity>();
//		try {
//			
//			boolean  result=service.delete(entity);
//		
//		} catch (Exception e) {
//			// TODO: handle exception
//			res.error();
//			res.setMessage(e.toString());
//		}
//		return res;
//	}
	
}
