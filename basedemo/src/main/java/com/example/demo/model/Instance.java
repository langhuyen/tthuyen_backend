package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/***
 * Lưu thông tin mooc,container,truck
 * @author huyen.tt166212
 * @params: type=MOOC,CONTAINER,TRUCK
 *@params: returnDepotCodes các depot của loại này có thể trả về của 1 khách hàng
 */
@Document(collection = "instance")
public class Instance extends BaseModel {
	@Id
	private String id;
	private Float weight= new Float(0.0);
	private String depotCode;
	private String depotLocationCode;
	private List<String> returnDepotCodes;
	private List<Interval>  intervals;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public String getDepotCode() {
		return depotCode;
	}
	public void setDepotCode(String depotCode) {
		this.depotCode = depotCode;
	}
	public String getDepotLocationCode() {
		return depotLocationCode;
	}
	public void setDepotLocationCode(String depotLocationCode) {
		this.depotLocationCode = depotLocationCode;
	}
	public List<String> getReturnDepotCodes() {
		return returnDepotCodes;
	}
	public void setReturnDepotCodes(List<String> returnDepotCodes) {
		this.returnDepotCodes = returnDepotCodes;
	}
	public List<Interval> getIntervals() {
		return intervals;
	}
	public void setIntervals(List<Interval> intervals) {
		this.intervals = intervals;
	}
	
	
}
