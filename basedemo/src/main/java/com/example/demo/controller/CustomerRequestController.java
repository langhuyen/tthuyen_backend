package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomerRequest;
import com.example.demo.model.CustomerRequestRefModel;
import com.example.demo.model.DateParam;
import com.example.demo.model.Entity;
import com.example.demo.model.ResponseModel;
import com.example.demo.service.CustomerRequestService;

@RestController
@RequestMapping("/CustomerRequest")
public class CustomerRequestController extends  BaseController<CustomerRequest, String > {

	
	@GetMapping("/getType/:{type}")
	public ResponseModel getByType(@PathVariable String type){
		ResponseModel res=new ResponseModel();
		List<CustomerRequestRefModel> lst=new ArrayList<CustomerRequestRefModel>();
		try {
			
			lst=((CustomerRequestService)service).getByTypeList(type);
			if(lst.size()==0) {
				res.notFound();
			}else {
				
				res.setData(lst);
			}
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	/**
	 * Viết hàm phân trang tính tổng
	 */
	@GetMapping("/getTypePaging/:{type}")
	public ResponseModel getByTypePaging(@PathVariable String type,@Param(value="pageIndex") int pageIndex,@Param(value="pageSize") int pageSize){
		ResponseModel res=new ResponseModel();
		List<CustomerRequestRefModel> lst=new ArrayList<CustomerRequestRefModel>();
		try {
			
			lst=((CustomerRequestService)service).getByTypeListPaging(type, pageIndex, pageSize);
			int totalPage=((CustomerRequestService)service).countByType(type, auth.getAuth("id"));
			if(lst.size()==0) {
				res.notFound();
			}else {
				
				res.setData(lst);
				res.setOther("totalPage", totalPage);
			}
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	@PostMapping("/getAllDay")
	public ResponseModel getListAllDay(@RequestBody Date date){
		ResponseModel res=new ResponseModel();
		List<CustomerRequestRefModel> lst=new ArrayList<CustomerRequestRefModel>();
		try {
			
			lst=((CustomerRequestService)service).getListRequestAllDay(date);
			if(lst.size()==0) {
				res.notFound();
			}else {
				
				res.setData(lst);
			}
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	@PostMapping("/getAllDayPaging")
	public ResponseModel getListAllDayPaging(@RequestBody DateParam date,@Param(value="pageIndex") int pageIndex,@Param(value="pageSize") int pageSize){
		ResponseModel res=new ResponseModel();
		List<CustomerRequestRefModel> lst=new ArrayList<CustomerRequestRefModel>();
		try {
			
			lst=((CustomerRequestService)service).getListRequestAllDayPaging(date, pageIndex, pageSize);
			int count=((CustomerRequestService)service).countRequestAllDayPaging(date);	
			if(lst.size()==0) {
				
				res.setData(lst);
				
				//Tinh tong các customer_request chưa được lập lịch
				res.setOther("totalPage", 0);
			}else {
				
				res.setData(lst);
				
				//Tinh tong các customer_request chưa được lập lịch
				res.setOther("totalPage", count);
			}
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}

}
