package com.example.demo.model;

import java.util.List;

/***
 * Model thực hiện map kết quả phân giải sang 
 * @author huyen.tt166212
 *
 */
public class ResultModel {

	private List<String> destination_addresses;
	private List<String> origin_addresses;
	private String status;
	private List<RowModel> rows;
	public List<String> getDestination_addresses() {
		return destination_addresses;
	}
	public void setDestination_addresses(List<String> destination_addresses) {
		this.destination_addresses = destination_addresses;
	}
	public List<String> getOrigin_addresses() {
		return origin_addresses;
	}
	public void setOrigin_addresses(List<String> origin_addresses) {
		this.origin_addresses = origin_addresses;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<RowModel> getRows() {
		return rows;
	}
	public void setRows(List<RowModel> rows) {
		this.rows = rows;
	}
	
	
	
}
