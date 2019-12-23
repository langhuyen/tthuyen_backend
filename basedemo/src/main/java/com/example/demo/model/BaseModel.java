package com.example.demo.model;

/**
 * Gôm các trường code :
 * code: ,
 * idCode là id kiểu số nguyên
 * type: Loại thực thể 
 * entity: depottruck,depottrailer,depotcontainer,port, warehouse
 * instance: gồm có container,mooc,truck
 *  
 * @author huyen.tt166212
 *
 */

public class BaseModel {

	
	protected String name;
	protected String code;
	protected String type;
	private String idCode;
	private String userId;
	private String description;
	
	
	
	
	

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUseId(String userId) {
		this.userId = userId;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
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
