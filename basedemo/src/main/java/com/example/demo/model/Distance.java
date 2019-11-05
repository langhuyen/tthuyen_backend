package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**Thong tin ma tran khoang cach
 * TTHuyen_29/10/2019
 * */
@Document(collection = "distance")
public class Distance extends BaseModel{
	@Id
	private String id;
	private String srcCode;
	private String desCode;
	private Boolean isDriveBalance=true;
	private Float distance;
	private Float travelTime;
	public String getSrcCode() {
		return srcCode;
	}
	public void setSrcCode(String srcCode) {
		this.srcCode = srcCode;
	}
	public String getDesCode() {
		return desCode;
	}
	public void setDesCode(String desCode) {
		this.desCode = desCode;
	}
	public Boolean getIsDriveBalance() {
		return isDriveBalance;
	}
	public void setIsDriveBalance(Boolean isDriveBalance) {
		this.isDriveBalance = isDriveBalance;
	}
	public Float getDistance() {
		return distance;
	}
	public void setDistance(Float distance) {
		this.distance = distance;
	}
	public Float getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(Float travelTime) {
		this.travelTime = travelTime;
	}
	

}
