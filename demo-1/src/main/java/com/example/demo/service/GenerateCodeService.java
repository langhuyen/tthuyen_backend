package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GenerateCode;
import com.example.demo.repository.IGenerateCodeRepository;

@Service
public class GenerateCodeService implements IGenerateCodeService {
	
	@Autowired
	IGenerateCodeRepository repo;
	@Override
	public GenerateCode editEntity(String type ) {
		// TODO Auto-generated method stub
		GenerateCode entity=repo.findByType(type);
		entity.setCode(entity.getCode()+1);
		return repo.save(entity);
	}

	@Override
	public String getByType(String type) {
		// TODO Auto-generated method stub
		GenerateCode code= repo.findByType(type);
		StringBuilder result=new StringBuilder(code.getPrefix());
		for(int i=0;i<9-code.getCode().toString().length();i++) {
			result.append("0");
		}
		result.append(code.getCode().toString());
		return result.toString();
	}
	
	
}
