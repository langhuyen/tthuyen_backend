package com.example.demo.model;

public class Node {
 private Integer locationCode;
 private String action;
 private String arrivalTime;
 private String departureTime;
 private Integer travelTime;
public Integer getLocationCode() {
	return locationCode;
}
public void setLocationCode(Integer locationCode) {
	this.locationCode = locationCode;
}
public String getAction() {
	return action;
}
public void setAction(String action) {
	this.action = action;
}
public String getArrivalTime() {
	return arrivalTime;
}
public void setArrivalTime(String arrivalTime) {
	this.arrivalTime = arrivalTime;
}
public String getDepartureTime() {
	return departureTime;
}
public void setDepartureTime(String departureTime) {
	this.departureTime = departureTime;
}
public Integer getTravelTime() {
	return travelTime;
}
public void setTravelTime(Integer travelTime) {
	this.travelTime = travelTime;
}
}
