package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomerRequestRefModel;
import com.example.demo.model.Instance;
import com.example.demo.model.ResponseModel;
import com.example.demo.service.CustomerRequestService;
import com.example.demo.service.InstanceService;

@RestController
@RequestMapping("/instance")
public class InstanceController extends BaseController<Instance, String> {
	
/**
 * Lấy các instance có là Type và đang rảnh rỗi
 */
	
	
	@GetMapping("/getInstanceFreeByType")
	public ResponseModel getInstanceFreeByType(){
		ResponseModel res=new ResponseModel();
		List<Instance> lst=new ArrayList<Instance>();
		try {
			
			lst=((InstanceService)service).getInstanceFreeByType();
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
}
