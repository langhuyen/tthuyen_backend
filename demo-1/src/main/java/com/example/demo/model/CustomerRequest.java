package com.example.demo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.model.ultilities.RequestType;


/*created:tthuyenn 08/09/2019
 * Model chứa thông tin các request của customer
 * 
 */
@Document(collection="customer_request")
public class CustomerRequest {
	@Id
	private String id;
	private String code;
	private String warehouseCode;
	private Date earlyPickupDateTime;
	private Date latePickupDateTime;
	private Date earlyDeliveryDateTime;
	private Date lateDeliveryDateTime;
	private String containerTypeCode;
	private Integer quantity;
	private RequestType type;
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
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public RequestType getType() {
		return type;
	}
	public void setType(RequestType type) {
		this.type = type;
	}
	public CustomerRequest(String id, String code, String warehouseCode, Date earlyPickupDateTime,
			Date latePickupDateTime, Date earlyDeliveryDateTime, Date lateDeliveryDateTime, String containerTypeCode,
			Integer quantity, RequestType type) {
		super();
		this.id = id;
		this.code = code;
		this.warehouseCode = warehouseCode;
		this.earlyPickupDateTime = earlyPickupDateTime;
		this.latePickupDateTime = latePickupDateTime;
		this.earlyDeliveryDateTime = earlyDeliveryDateTime;
		this.lateDeliveryDateTime = lateDeliveryDateTime;
		this.containerTypeCode = containerTypeCode;
		this.quantity = quantity;
		this.type = type;
	}
	public CustomerRequest() {
		super();
	}
	
	
	

}
