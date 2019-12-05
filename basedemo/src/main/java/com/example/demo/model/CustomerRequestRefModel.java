package com.example.demo.model;

import java.util.Date;
import java.util.List;

/****
 * Model lưu trữ thông tin của các request có ref đến container warehouse port
 * @author huyen.tt166212
 *
 */
public class CustomerRequestRefModel{
	private String id;
	private String code;
	private String type;
	private Integer idCode;
	private List<Entity> warehouseCode;
	private List<Entity> portCode;
	private List<Instance> containerCode;
	private String depotContainerCode;
	private Date earlyPickupDateTime;
	private Date latePickupDateTime;
	private Date earlyDeliveryDateTime;
	private Date lateDeliveryDateTime;
	private String containerTypeCode;
	private Integer quantity;
	private Date requestDate=new Date();
	
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Entity> getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(List<Entity> warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public List<Entity> getPortCode() {
		return portCode;
	}
	public void setPortCode(List<Entity> portCode) {
		this.portCode = portCode;
	}
	public List<Instance> getContainerCode() {
		return containerCode;
	}
	public void setContainerCode(List<Instance> containerCode) {
		this.containerCode = containerCode;
	}
	public String getDepotContainerCode() {
		return depotContainerCode;
	}
	public void setDepotContainerCode(String depotContainerCode) {
		this.depotContainerCode = depotContainerCode;
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
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	

}
