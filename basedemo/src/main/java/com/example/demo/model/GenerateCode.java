package com.example.demo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="generate_code")
public class GenerateCode extends BaseModel implements Serializable{
	@Id
	private String id;
	private String prefix;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	

	

}
