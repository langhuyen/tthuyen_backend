package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.GenerateCode;
import com.example.demo.model.ResponseModel;
import com.example.demo.service.GenerateCodeService;
import com.example.demo.service.IBaseService;


@RestController
public class GenerateCodeController {

	@Autowired
	IBaseService<GenerateCode> codeService;
	
	@GetMapping("/AutoGenerateCode/:{type}")
	public ResponseModel get(@PathVariable String type){
		ResponseModel res=new ResponseModel();
		List<String> lst=new ArrayList<String>();
		try {
			
			lst.add(((GenerateCodeService)codeService).getByOtherType(type));
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
