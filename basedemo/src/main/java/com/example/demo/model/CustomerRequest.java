package com.example.demo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/*created:tthuyenn 08/09/2019
 * Model chứa thông tin các request của customer
 * 
 */
@Document(collection="customer_request")
public class CustomerRequest extends BaseModel {
	@Id
	private String id;
	private String warehouseCode;
	private String portCode;
	private String containerCode;
	private String depotContainerCode;
	private Date earlyPickupDateTime;
	private Date latePickupDateTime;
	private Date earlyDeliveryDateTime;
	private Date lateDeliveryDateTime;
	private String containerTypeCode;
	private Float weight= new Float(0.0);
	@SuppressWarnings("deprecation")
	private Date requestDate=new Date(new Date().getYear(),new Date().getMonth(),new Date().getDate(),12,0,0);
	private Boolean isSchedule=false;
	
	public Boolean getIsSchedule() {
		return isSchedule;
	}
	public void setIsSchedule(Boolean isSchedule) {
		this.isSchedule = isSchedule;
	}
	public String getDepotContainerCode() {
		return depotContainerCode;
	}
	public void setDepotContainerCode(String depotContainerCode) {
		this.depotContainerCode = depotContainerCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getPortCode() {
		return portCode;
	}
	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}
	public String getContainerCode() {
		return containerCode;
	}
	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}
	public Date getEarlyPickupDateTime() {
		return earlyPickupDateTime;
	}
	public void setEarlyPickupDateTime(Date earlyPickupDateTime) {
		this.earlyPickupDateTime = earlyPickupDateTime;
	}
	public Date getLatePickupDateTime() {
		return latePickupDateTime;
	}
	public void setLatePickupDateTime(Date latePickupDateTime) {
		this.latePickupDateTime = latePickupDateTime;
	}
	public Date getEarlyDeliveryDateTime() {
		return earlyDeliveryDateTime;
	}
	public void setEarlyDeliveryDateTime(Date earlyDeliveryDateTime) {
		this.earlyDeliveryDateTime = earlyDeliveryDateTime;
	}
	public Date getLateDeliveryDateTime() {
		return lateDeliveryDateTime;
	}
	public void setLateDeliveryDateTime(Date lateDeliveryDateTime) {
		this.lateDeliveryDateTime = lateDeliveryDateTime;
	}
	public String getContainerTypeCode() {
		return containerTypeCode;
	}
	public void setContainerTypeCode(String containerTypeCode) {
		this.containerTypeCode = containerTypeCode;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public CustomerRequest(String id, String warehouseCode, String portCode, String containerCode,
			Date earlyPickupDateTime, Date latePickupDateTime, Date earlyDeliveryDateTime, Date lateDeliveryDateTime,
			String containerTypeCode, Float weight, Date requestDate) {
		super();
		this.id = id;
		this.warehouseCode = warehouseCode;
		this.portCode = portCode;
		this.containerCode = containerCode;
		this.earlyPickupDateTime = earlyPickupDateTime;
		this.latePickupDateTime = latePickupDateTime;
		this.earlyDeliveryDateTime = earlyDeliveryDateTime;
		this.lateDeliveryDateTime = lateDeliveryDateTime;
		this.containerTypeCode = containerTypeCode;
		this.weight = weight;
		this.requestDate = requestDate;
	}
	public CustomerRequest() {
		super();
	}
	
	
	


	

}
