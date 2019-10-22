package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Entity;
import com.example.demo.model.GenerateCode;
import com.example.demo.repository.IGenerateCodeRepository;

public interface IGenerateCodeService {

	public GenerateCode editEntity(String type );
	public String getByType(String type);
}
