package com.example.demo.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/***
 * Model lưu các chuyến vận tải
 * @author huyen.tt166212
 *
 */

@Document(collection="truck_router")
public class TruckRoute {
	@Id
	private String id;
	private Object truck;
	private Integer nbStops;
	private Integer travelTime=0;
	private List<Object> nodes;
	@SuppressWarnings("deprecation")
	private Date createdDate=new Date(new Date().getYear(),new Date().getMonth(),new Date().getDate(),12,0,0);
	private String userId;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Object getTruck() {
		return truck;
	}
	public void setTruck(Object truck) {
		this.truck = truck;
	}
	public Integer getNbStops() {
		return nbStops;
	}
	public void setNbStops(Integer nbStops) {
		this.nbStops = nbStops;
	}
	public Integer getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(Integer travelTime) {
		this.travelTime = travelTime;
	}
	public List<Object> getNodes() {
		return nodes;
	}
	public void setNodes(List<Object> nodes) {
		this.nodes = nodes;
	}
	public TruckRoute(String id, Object truck, Integer nbStops, Integer travelTime, List<Object> nodes) {
		super();
		this.id = id;
		this.truck = truck;
		this.nbStops = nbStops;
		this.travelTime = travelTime;
		this.nodes = nodes;
	}
	public TruckRoute() {
		
	}
	

}
