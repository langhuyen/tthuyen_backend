package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GenerateCode;
import com.example.demo.ultilities.Auth;

@Service
public class GenerateCodeService extends BaseService<GenerateCode,String>{

	@Autowired
	Auth auth;
	public GenerateCodeService() {
		this.entityClass=GenerateCode.class;
		// TODO Auto-generated constructor stub
	}
	public String getByOtherType(String type) {
		// TODO Auto-generated method stub
	
		if(!repo.findByType(type,auth.getAuth("id")).isEmpty()) {
			
			GenerateCode code= repo.findByType(type,auth.getAuth("id")).get(0);
			StringBuilder result=new StringBuilder(code.getPrefix());
			for(int i=0;i<9-code.getCode().toString().length();i++) {
				result.append("0");
			}
			result.append(Integer.parseInt(code.getCode()));
			return result.toString();
		}
		return null;
	}
	public GenerateCode getIdCodeByType(String type) {
		if(!repo.findByType(type,auth.getAuth("id")).isEmpty()) {
			
			GenerateCode code= repo.findByType(type,auth.getAuth("id")).get(0);
			return code;
		}
		return null;
	}

	public GenerateCode editType(String type ) {
		// TODO Auto-generated method stub
		GenerateCode entity=repo.findByType(type,auth.getAuth("id")).get(0);
		Integer code=Integer.parseInt(entity.getCode())+1;
		entity.setCode(code.toString());
		return repo.save(entity);
	}
}
