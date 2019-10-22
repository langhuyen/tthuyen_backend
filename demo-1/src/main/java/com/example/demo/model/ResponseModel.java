package com.example.demo.model;

import java.util.List;

public class ResponseModel {
	
	public boolean success;
	public int code;
	public String message;
	public List<?> data;
	public ResponseModel() {
		this.code=0;//Thành công;
		this.success=true;
	}
	public void error() {
		this.success=false;
		this.code=999;
	}
	public void notFound() {
		this.success=true;
		this.code=2;
	}
	public void invalid() {
		this.success=false;
		this.code=1000;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
		
	
}
