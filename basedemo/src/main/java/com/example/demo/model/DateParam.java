package com.example.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DateParam {
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date toDate;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date fromDate;
	public Date getToDate() {
		return toDate;
	}
	
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	

}
