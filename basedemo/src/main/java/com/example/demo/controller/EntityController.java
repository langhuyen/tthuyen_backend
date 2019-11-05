package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Distance;
import com.example.demo.model.Entity;
import com.example.demo.model.GenerateCode;
import com.example.demo.model.ResponseModel;
import com.example.demo.model.ResultModel;
import com.example.demo.repository.IBaseRepository;
import com.example.demo.service.EntityService;
import com.example.demo.service.GenerateCodeService;
import com.example.demo.service.IBaseService;

import net.minidev.json.JSONObject;


@RestController
@RequestMapping("/entity")
public class EntityController extends BaseController<Entity, String > {
	@Autowired 
	IBaseService<Distance> distanceService;

	
//	@PostMapping("/insert")
//	public ResponseModel insert(@RequestBody Entity entity){
//		ResponseModel res=new ResponseModel();
//		List<Entity> lst=new ArrayList<Entity>();
//		try {
//			String codeSys=((GenerateCodeService)codeService).getByOtherType(entity.getType().toString());
//			if(entity.getCode().equals(codeSys)) {
//				((GenerateCodeService)codeService).editType(entity.getType().toString());
//				};
//			Entity en=service.insertEntity(entity);
//			lst.add(en);
//			res.setData(lst);
//		} catch (Exception e) {
//			// TODO: handle exception
//			res.error();
//			res.setMessage(e.toString());
//		}
//		return res;
//	}
	
	
	/***
	 * Thực hiện Lấy danh sách các depot khách port và warehouse
	 * 
	 */
	@GetMapping("/getDepot")
	public ResponseModel getEntityMiddle(){
		ResponseModel res=new ResponseModel();
		List<Entity> lst=new ArrayList<Entity>();
		try {
			
			lst=((EntityService)service).getEntityMiddle();
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
	 
	    static final String KeyAPI = "AIzaSyCgBE4hSryEhlwDwTlIagZSJXY-wpp_-N0";
	    static final String URL_EMPLOYEES_JSON = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=";
//	    		+ "Washington,DC&destinations=New+York+City,NY&key=AIzaSyCgBE4hSryEhlwDwTlIagZSJXY-wpp_-N0";
	    
	@GetMapping("/test")
	public String getTest() {
		Date date=new Date();
		@Deprecated
	 	Date dateStart=new Date(date.getYear(), date.getMonth(), date.getDate(), 0, 0, 0);
		Date dateEnd=new Date(date.getYear(), date.getMonth(), date.getDate(), 23, 59, 59);
		
	        
	        
		 doUpdateDistance();
		 return "OK";
	}
	
	
	/**
	 * Thuc hien update khoang cach cac vi tri
	 */
	
	  public void doUpdateDistance() 
	    { 
	  
	        
	        new Thread(new Runnable() { 
	            public void run() 
	            { 	 RestTemplate restTemplate = new RestTemplate();
	   		 List<Entity> lst=service.getList();
			 for(int x=0;x<lst.size()-1;x++) {
				 Entity entity=lst.get(x);
				 for(int k=x+1;k<lst.size();k++) {
					 Entity entityNext=lst.get(k);
					 StringBuilder req=new StringBuilder(URL_EMPLOYEES_JSON);
					 req.append(entity.getAddress());
					 req.append("&destinations=");
					 req.append(entityNext.getAddress());
					 req.append("&key=");
					 req.append(KeyAPI);
					 
					 ResultModel result = restTemplate.getForObject(req.toString(),ResultModel.class);
					 if(result.getRows().get(0).getElements().get(0).getDistance()==null) continue;
					 Float distance=result.getRows().get(0).getElements().get(0).getDistance().getValue();
					 Float duration=result.getRows().get(0).getElements().get(0).getDuration().getValue();
					 Distance distanceInstance=new Distance();
					 distanceInstance.setSrcCode(entity.getId());
					 distanceInstance.setDesCode(entityNext.getId());
					 distanceInstance.setDistance(distance);
					 distanceInstance.setTravelTime(duration);
					 Distance distanceRevert=new Distance();
					 distanceRevert.setDesCode(entity.getId());
					 distanceRevert.setSrcCode(entityNext.getId());
					 distanceRevert.setDistance(distance);
					 distanceRevert.setTravelTime(duration);
					 distanceService.insertEntity(distanceInstance);
//					try {
//						
//						Thread.sleep(100);
//					} catch (Exception e) {
//						// TODO: handle exception
//					} 
					distanceService.insertEntity(distanceRevert);
//					try {
//						
//						Thread.sleep(1000);
//					} catch (Exception e) {
//						// TODO: handle exception
//					} 
				 }
			 }
			
	            } 
	        }).start(); 
	    } 

}
