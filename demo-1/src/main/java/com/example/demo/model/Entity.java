package com.example.demo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.model.ultilities.EntityType;




/*created:tthuyenn 08/09/2019
 * Model chứa thông tin các đối tượng cần quản lý
 * 
 */
@Document(collection="entity")
public class Entity implements Serializable{
	
	 @Id
	private String id;
	private String code;
	private String name;
	private LatLng latLng;
	private String address;
	private EntityType type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public LatLng getLatLng() {
		return latLng;
	}
	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public EntityType getType() {
		return type;
	}
	public void setType(EntityType type) {
		this.type = type;
	}
	public Entity(String id, String code, String name, LatLng latLng, String address, EntityType type) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.latLng = latLng;
		this.address = address;
		this.type = type;
	}
	public Entity() {
		super();
	}
	
	
	
	
}
