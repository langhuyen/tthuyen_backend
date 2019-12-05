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
import com.example.demo.service.DistanceService;
import com.example.demo.service.EntityService;
import com.example.demo.service.GenerateCodeService;
import com.example.demo.service.IBaseService;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/entity")
public class EntityController extends BaseController<Entity, String> {
	@Autowired
	IBaseService<Distance, String> distanceService;
	static final String KeyAPI1 = "AIzaSyBuBc81YVlifiWhiORZRxQhtS3m_kFCIHI";

	// Chút bỏ cái key này nhé
	static final String KeyAPI2 = "AIzaSyAzJdGMjcgSDs0wmCYPPg6YK9jG0vOY6_s";
	static final String URL_API = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=";

	
	@PostMapping("/insert")
	@Override
	public  ResponseModel insert(@RequestBody Entity entity){
		ResponseModel res=new ResponseModel();
		List<Entity> lst=new ArrayList<Entity>();
		try {
			String codeSys=((GenerateCodeService)codeService).getByOtherType(entity.getType().toString());
			if(entity.getCode().equals(codeSys)) {
				((GenerateCodeService)codeService).editType(entity.getType().toString());
				};
				GenerateCode idCode=((GenerateCodeService)codeService).getIdCodeByType(entity.getType().toString());
				entity.setIdCode(autoId.getNextSequence("auto_id"));
			Entity en=service.insertEntity(entity);
			lst.add(en);
			doUpdateDistance(en);
			res.setData(lst);
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}
	
	@PostMapping("/update/postion")
	public ResponseModel updatePosition(@RequestBody Entity entity) {
		ResponseModel res = new ResponseModel();
		List<Entity> lst = new ArrayList<Entity>();
		try {
			Entity en = service.editEntity(entity);
			lst.add(en);
			res.setData(lst);
			doUpdateDistance(en);
		} catch (Exception e) {
			// TODO: handle exception
			res.error();
			res.setMessage(e.toString());
		}
		return res;
	}

	/***
	 * Thực hiện Lấy danh sách các depot khách port và warehouse
	 * 
	 */
	@GetMapping("/getDepot")
	public ResponseModel getEntityMiddle() {
		ResponseModel res = new ResponseModel();
		List<Entity> lst = new ArrayList<Entity>();
		try {

			lst = ((EntityService) service).getEntityMiddle();
			if (lst.size() == 0) {
				res.notFound();
			} else {

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
	 * Thuc hien update khoang cach cac vi tri
	 */
	
	  public void doUpdateDistance(Entity entity) 
	    { 
	  
	        
	        new Thread(new Runnable() { 
	            public void run() 
	            { 	 RestTemplate restTemplate = new RestTemplate();
	   		 List<Entity> lst=service.getList();
			 for(int x=0;x<lst.size();x++) {
				 Entity entityOther=lst.get(x);
				 if(entity.getId().equals(entityOther.getId()))continue;
					
					 StringBuilder req=new StringBuilder(URL_API);
					 req.append(entity.getLatLng().getLat()+","+ entity.getLatLng().getLng());
					 req.append("&destinations=");
					 req.append(entityOther.getLatLng().getLat()+","+entityOther.getLatLng().getLng());
					 req.append("&key=");
					 if(x%2==0) {
						 req.append(KeyAPI1);
						 
					 }else {
						 req.append(KeyAPI2);
					 }
					 try {
						 Thread.sleep(1000);
						 ResultModel result = restTemplate.getForObject(req.toString(),ResultModel.class);
						 System.out.println(result.getStatus());
						 if(result.getRows().get(0).getElements().get(0).getDistance()==null) continue;
						 Float distance=result.getRows().get(0).getElements().get(0).getDistance().getValue();
						 Float duration=result.getRows().get(0).getElements().get(0).getDuration().getValue();
						
						 Distance distanceInstance=new Distance();
						 distanceInstance.setSrcCode(entity.getLocationCode());
						 distanceInstance.setDesCode(entityOther.getLocationCode());
						 distanceInstance.setDistance(distance);
						 distanceInstance.setTravelTime(duration);
						
						 
						 Distance distanceRevert=new Distance();
						 distanceRevert.setDesCode(entity.getLocationCode());
						 distanceRevert.setSrcCode(entityOther.getLocationCode());
						 distanceRevert.setDistance(distance);
						 distanceRevert.setTravelTime(duration);
						 distanceService.insertEntity(distanceInstance);
						 distanceService.insertEntity(distanceRevert);
					} catch (Exception e) {
						// TODO: handle exception
					}

				 }
			 }
			
	        }).start();
}

}
