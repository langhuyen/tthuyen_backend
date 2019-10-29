package com.example.demo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




/*created:tthuyenn 08/09/2019
 * Model chứa thông tin các đối tượng cần quản lý(Depot)
 * Id Tương đương với locationCode
 */
@Document(collection="entity")
public class Entity extends BaseModel implements Serializable{
	
	 @Id
	private String id;

	private LatLng latLng;
	private String address;
	
//	private EntityType type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
//	public EntityType getType() {
//		return type;
//	}
//	public void setType(EntityType type) {
//		this.type = type;
//	}
	public Entity(String id, String code, String name, LatLng latLng, String address, String type) {
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
