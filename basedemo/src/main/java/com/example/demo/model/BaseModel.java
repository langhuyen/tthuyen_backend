package com.example.demo.model;

public class BaseModel {

	
	protected String name;
	protected String type;
	protected String code;
	private Integer idCode;
	
	

	public Integer getIdCode() {
		return idCode;
	}
	public void setIdCode(Integer idCode) {
		this.idCode = idCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
