package com.example.demo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.Field;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomerRequestRefModel;
import com.example.demo.model.Distance;
import com.example.demo.model.Entity;
import com.example.demo.model.Instance;
import com.example.demo.model.TruckRoute;
import com.example.demo.repository.IBaseRepository;
import com.example.demo.repository.ITruckRouterRepository;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@Service
public class TransportService {

	 @Autowired
	    private MongoTemplate mongoTemplate;
	 @Autowired
	IBaseRepository< Instance, String> instanceRepo;
	 @Autowired
	 IBaseRepository<Entity,String> entityRepo;
	 @Autowired
	 DistanceService distanceService;
	 @Autowired
	 ITruckRouterRepository truckRepo;
	 ClassLoader classLoader = getClass().getClassLoader();
	 String path  = classLoader.getResource("JsonRequest").getPath().replaceFirst("/", "");
	 
//	 @Autowired
//	    private MongoTemplate mongoTemplate;

	 /**
	  * Tra ve cac truck_router
	  * @param output
	  * @return
	  */
	 public List<Object> getTruck(){
		 
		 LookupOperation lookupOperation = LookupOperation.newLookup()
                 .from("entity")
                 .localField("locationCode")
                 .foreignField("locationCode")
                 .as("nodes.address");
		AggregationOperation ac= new AggregationOperation() {
				
				@Override
				public Document toDocument(AggregationOperationContext context) {
					// TODO Auto-generated method stub
					JSONObject obj=new JSONObject();
					obj.put("_id", "$_id");
					obj.put("nodes",new Document("$push","$nodes"));
					return new Document("$group",new Document(obj));
				}
			};
		    Aggregation aggregation = Aggregation.newAggregation(
		    		Aggregation.unwind("nodes"),
		    		new AggregationOperation() {
						
						@Override
						public Document toDocument(AggregationOperationContext context) {
							// TODO Auto-generated method stub
							
							return new Document("$addFields",new Document("locationCode",new Document("$toInt","$nodes.locationCode")));
						}
					},
		    		lookupOperation,
		    		ac
		    		
		    		);
		    List<Object> trucks = mongoTemplate.aggregate(aggregation, "truck_router", Object.class).getMappedResults();
		     
		 return trucks;
	 }
	 
	 
	 public  boolean ReadFileAndWriteToDB(String output){
		 
		//JSON parser object to parse read file
		 @Deprecated
	        JSONParser jsonParser = new JSONParser();
	         
	        try (FileReader reader = new FileReader(path+"/output/test-result.json"))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            JSONObject data = (JSONObject) obj;
	            JSONArray truckRouteRaw=(JSONArray)data.get("truckRoutes");
	             for(Object item :truckRouteRaw) {
	            	 	try {
	            	 		JSONObject json=(JSONObject) item;
	            	 		TruckRoute truck=new TruckRoute();
	            	 		truck.setTruck(json.get("truck"));
	            	 		truck.setNodes((List<Object>)json.get("nodes"));
	            	 		truck.setNbStops((Integer)json.get("nbStops"));
	            	 		truck.setTravelTime((Integer)json.get("travelTime"));
	            	 		truckRepo.insert(truck);
						} catch (Exception e) {
							// TODO: handle exception
						e.printStackTrace();
						}
	             }
	          
	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		 return true;
	 }
	 private boolean excutedAlgorithm(String input) {
		 
			boolean isWindows = System.getProperty("os.name")
					  .toLowerCase().startsWith("windows");
						 System.out.println(path);

			StringBuilder cmd = new StringBuilder("java -jar ");
			 cmd.append(path +"/truckcontainer.jar --input ");
			cmd.append(path+"/"+input+".json --output ");
			cmd.append(path+"/output/"+input+"-result.json");
			
			 System.out.println(cmd.toString());
			try {
			    Process process = Runtime.getRuntime().exec(cmd.toString());
			 
			    BufferedReader reader = new BufferedReader(
			            new InputStreamReader(process.getInputStream()));
			    String line;
			    while ((line = reader.readLine()) != null) {
			        System.out.println(line);
			    }
			 
			    reader.close();
			 
			} catch (IOException e) {
			    e.printStackTrace();
			    return false;
			}
		return true;
	 }
	 
	 /**
	  * Tạo các  Instance gồm các MOOC và Truck
	  * @param idLst
	  */
	 private JSONObject convertToInstance() {
		 List<Instance> lstMooc= instanceRepo.findByType("MOOC");
		 List<Instance> lstTruck= instanceRepo.findByType("TRUCK");
		 JSONObject result=new JSONObject();
		 List<JSONObject> moocArr=new ArrayList<>();
		 for(int i=0;i<lstMooc.size();i++) {
			 Instance item=lstMooc.get(i);
			 JSONObject moocIns=new JSONObject();
			 moocIns.put("id", i);
			 moocIns.put("code", item.getIdCode());
			 moocIns.put("weight",item.getWeight());
			 moocIns.put("status", item.getStatus());
			 moocIns.put("returnDepotCodes", item.getReturnDepotCodes());
			 moocIns.put("depotMoocCode",item.getDepotCode());
			moocIns.put("depotMoocLocationCode", item.getDepotLocationCode());
			moocIns.put("intervals", item.getIntervals());
			moocArr.add(moocIns);
		 }
		 result.put("moocs", moocArr);
		 List<JSONObject> truckArr=new ArrayList<>();
		 for(int i=0;i<lstTruck.size();i++) {
			 Instance item=lstTruck.get(i);
			 JSONObject truckIns=new JSONObject();
			 truckIns.put("id", i);
			 truckIns.put("code", item.getIdCode());
			 truckIns.put("weight",item.getWeight());
			 truckIns.put("status", item.getStatus());
			 truckIns.put("returnDepotCodes", item.getReturnDepotCodes());
			 truckIns.put("depotTruckCode",item.getDepotCode());
			 truckIns.put("depotTruckLocationCode", item.getDepotLocationCode());
			 truckIns.put("intervals", item.getIntervals());
			 truckArr.add(truckIns);
		 }
		 result.put("trucks", truckArr);
		 return result;
	 }
	 
	 /***
	  * Thực hiện lấy danh sách các depot của 
	  * DEPOTTRUCK,DEPOTCONTAINER,và DEPOTTRAILER
	  * @return
	  */
	 private JSONObject convertToDepot() {
		 List<Entity> lstDepotTruck= entityRepo.findByType("DEPOTTRUCK");
		 List<Entity> lstDepotContainer= entityRepo.findByType("DEPOTCONTAINER");
		 List<Entity> lstDepotTrailer= entityRepo.findByType("DEPOTTRAILER");
		 JSONObject result=new JSONObject();
		 List<JSONObject> dpTruckArr=new ArrayList<>();
		for(Entity dpTruck:lstDepotTruck) {
			JSONObject dpTruckObj=new JSONObject();
			dpTruckObj.put("code", dpTruck.getCode());
			dpTruckObj.put("locationCode", dpTruck.getLocationCode());
			dpTruckObj.put("pickupContainerDuration", 0);
			dpTruckObj.put("deliveryContainerDuration", 0);
			dpTruckArr.add(dpTruckObj);
		}
		result.put("depotTrucks", dpTruckArr);
		 List<JSONObject> dpContainerArr=new ArrayList<>();
		for(Entity dpContainer:lstDepotContainer) {
			JSONObject dpContainerObj=new JSONObject();
			dpContainerObj.put("code", dpContainer.getCode());
			dpContainerObj.put("locationCode", dpContainer.getLocationCode());
			dpContainerObj.put("pickupContainerDuration", 0);
			dpContainerObj.put("deliveryContainerDuration", 0);
			dpContainerArr.add(dpContainerObj);
			
		}
		result.put("depotContainers", dpContainerArr);
		List<JSONObject> dpTrailerArr=new ArrayList<>();
		for(Entity dpTrailer:lstDepotTrailer) {
			JSONObject dpTrailerObj=new JSONObject();
			dpTrailerObj.put("code", dpTrailer.getCode());
			dpTrailerObj.put("locationCode", dpTrailer.getLocationCode());
			dpTrailerObj.put("pickupMoocDuration", 0);
			dpTrailerObj.put("deliveryMoocDuration", 0);
			dpTrailerArr.add(dpTrailerObj);
		}
		result.put("depotMoocs", dpTrailerArr);
		
		 return result;
	 }
	 
	 public void createdTrip(List<CustomerRequestRefModel> customerRequestRefModels) {
		 JSONObject jsonObjec=new JSONObject();

    	String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	List<JSONObject> ildArr=new ArrayList<>();
    	List<JSONObject> ierArr=new ArrayList<>();
    	List<JSONObject> eerArr=new ArrayList<>();
    	List<JSONObject> eldArr=new ArrayList<>();
    	List<JSONObject> warehouseArr=new ArrayList<>();
    	List<JSONObject> portArr=new ArrayList<>();
    	List<JSONObject> containerArr=new ArrayList<>();
    	for(int i=0;i<customerRequestRefModels.size();i++) {
    	CustomerRequestRefModel	item=customerRequestRefModels.get(i);
    		
    	if(!item.getWarehouseCode().isEmpty()) {
    		JSONObject warehouse=new JSONObject();
    		warehouse.put("code", item.getWarehouseCode().get(0).getCode());
    		warehouse.put("locationCode", item.getWarehouseCode().get(0).getLocationCode());
    		warehouse.put("hardConstraintType", 0);
//    		warehouse.put("drivers", null);
    		warehouse.put("vehicleConstraintType", -1);
    		warehouseArr.add(warehouse);
    		}
    	if(!item.getPortCode().isEmpty()) {
    		JSONObject port=new JSONObject();
    		port.put("code", item.getPortCode().get(0).getCode());
    		port.put("locationCode", item.getPortCode().get(0).getLocationCode());
    		portArr.add(port);
    	}
    	if(!item.getContainerCode().isEmpty()) {
    		JSONObject container=new JSONObject();
    		container.put("code", item.getContainerCode().get(0).getIdCode());
    		container.put("weight", item.getContainerCode().get(0).getWeight());
    		container.put("depotContainerCode", item.getContainerCode().get(0).getDepotLocationCode());
    		container.put("returnDepotCodes", item.getContainerCode().get(0).getReturnDepotCodes());
    		containerArr.add(container);
    	}
    	
    		if(item.getType().equals("ImportLadenRequest")) {
    			JSONObject importLadenRequest=new JSONObject();
    			importLadenRequest.put("id", i);
    			importLadenRequest.put("isBreakRomooc", true);
    			if(!item.getContainerCode().isEmpty()) {
    				
    				importLadenRequest.put("containerCode",item.getContainerCode().get(0).getIdCode());
    				importLadenRequest.put("containerNo",item.getContainerCode().get(0).getCode());
    			}
    			importLadenRequest.put("requestDate", simpleDateFormat.format(item.getRequestDate()));
    			importLadenRequest.put("earlyDateTimePickupAtPort", simpleDateFormat.format(item.getEarlyPickupDateTime()));
    			importLadenRequest.put("lateDateTimePickupAtPort", simpleDateFormat.format(item.getLatePickupDateTime()));
    			importLadenRequest.put("earlyDateTimeUnloadAtWarehouse", simpleDateFormat.format(item.getEarlyDeliveryDateTime()));
    			importLadenRequest.put("lateDateTimeUnloadAtWarehouse", simpleDateFormat.format(item.getLateDeliveryDateTime()));
    			importLadenRequest.put("portCode", item.getPortCode().get(0).getCode());
    			importLadenRequest.put("wareHouseCode", item.getWarehouseCode().get(0).getCode());
    			
    			ildArr.add(importLadenRequest);

    		}else if(item.getType().equals("ImportEmptyRequest")){
    			
    			JSONObject importEmptyRequest=new JSONObject();
    			importEmptyRequest.put("id", i);
    			importEmptyRequest.put("isBreakRomooc", true);
    			if(!item.getContainerCode().isEmpty()) {
    				
    				importEmptyRequest.put("containerCode",item.getContainerCode().get(0).getIdCode());
    				importEmptyRequest.put("containerNo",item.getContainerCode().get(0).getCode());
    				importEmptyRequest.put("depotContainerCode", item.getContainerCode().get(0).getDepotCode());
    			}
    			importEmptyRequest.put("requestDate", simpleDateFormat.format(item.getRequestDate()));
    			importEmptyRequest.put("earlyDateTimeAttachAtWarehouse", simpleDateFormat.format(item.getEarlyPickupDateTime()));
    			importEmptyRequest.put("lateDateTimeReturnEmptyAtDepot", simpleDateFormat.format(item.getLateDeliveryDateTime()));
//    			importEmptyRequest.put("earlyDateTimeUnloadAtWarehouse", simpleDateFormat.format(item.getEarlyDeliveryDateTime()));
//    			importEmptyRequest.put("lateDateTimeUnloadAtWarehouse", simpleDateFormat.format(item.getLateDeliveryDateTime()));
//    			importEmptyRequest.put("portCode", item.getPortCode().get(0).getCode());
    			importEmptyRequest.put("wareHouseCode", item.getWarehouseCode().get(0).getCode());
    			
    			ierArr.add(importEmptyRequest);
    			
    		}else if(item.getType().equals("ExportEmptyRequest")) {
    			JSONObject exportEmptyRequest=new JSONObject();
    			exportEmptyRequest.put("id", i);
    			exportEmptyRequest.put("isBreakRomooc", true);
    			if(!item.getContainerCode().isEmpty()) {
    				
    				exportEmptyRequest.put("containerCode",item.getContainerCode().get(0).getIdCode());
    				exportEmptyRequest.put("containerNo",item.getContainerCode().get(0).getCode());
    				exportEmptyRequest.put("depotContainerCode",item.getContainerCode().get(0).getDepotCode());
    			}
    			exportEmptyRequest.put("requestDate", simpleDateFormat.format(item.getRequestDate()));
    			exportEmptyRequest.put("earlyDateTimePickupAtDepot", simpleDateFormat.format(item.getEarlyPickupDateTime()));
    			exportEmptyRequest.put("lateDateTimePickupAtDepot", simpleDateFormat.format(item.getLatePickupDateTime()));
    			exportEmptyRequest.put("earlyDateTimeLoadAtWarehouse", simpleDateFormat.format(item.getEarlyDeliveryDateTime()));
    			exportEmptyRequest.put("lateDateTimeLoadAtWarehouse", simpleDateFormat.format(item.getLateDeliveryDateTime()));
//    			exportEmptyRequest.put("portCode", item.getPortCode().get(0).getCode());
    			exportEmptyRequest.put("wareHouseCode", item.getWarehouseCode().get(0).getCode());
    			
    			eerArr.add(exportEmptyRequest);
    			
    		}else if(item.getType().equals("ExportLadenRequest")) {
    			JSONObject exportLadenRequest=new JSONObject();
    			exportLadenRequest.put("id", i);
    			exportLadenRequest.put("isBreakRomooc", true);
    			if(!item.getContainerCode().isEmpty()) {
    				
    				exportLadenRequest.put("containerCode",item.getContainerCode().get(0).getIdCode());
    				exportLadenRequest.put("containerNo",item.getContainerCode().get(0).getCode());
//    				exportLadenRequest.put("depotContainerCode", item.getContainerCode().get(0).getDepotCode());
    			}
    			exportLadenRequest.put("requestDate", simpleDateFormat.format(item.getRequestDate()));
    			exportLadenRequest.put("earlyDateTimeAttachAtWarehouse", simpleDateFormat.format(item.getEarlyPickupDateTime()));
    			exportLadenRequest.put("lateDateTimeUnloadAtPort", simpleDateFormat.format(item.getLateDeliveryDateTime()));
//    			importEmptyRequest.put("earlyDateTimeUnloadAtWarehouse", simpleDateFormat.format(item.getEarlyDeliveryDateTime()));
//    			importEmptyRequest.put("lateDateTimeUnloadAtWarehouse", simpleDateFormat.format(item.getLateDeliveryDateTime()));
    			exportLadenRequest.put("portCode", item.getPortCode().get(0).getCode());
    			exportLadenRequest.put("wareHouseCode", item.getWarehouseCode().get(0).getCode());
    			
    			eldArr.add(exportLadenRequest);
    			
    		}
    	}
    	JSONObject result= new JSONObject();
    	JSONObject instances=convertToInstance();
    	result.put("moocs", instances.get("moocs"));
    	result.put("trucks", instances.get("trucks"));
    	JSONObject depot=convertToDepot();
    	result.put("depotTrucks", depot.get("depotTrucks"));
    	result.put("depotContainers", depot.get("depotContainers"));
    	result.put("depotMoocs", depot.get("depotMoocs"));
    	result.put("ports", portArr);
    	result.put("warehouses", warehouseArr);
    	result.put("containers", containerArr);
    	result.put("imLadenRequests", ildArr);
    	result.put("imEmptyRequests", ierArr);
    	result.put("exEmptyRequests", eerArr);
    	result.put("exLadenRequests", eldArr);
    	List<Distance> distance=distanceService.getList();
    	result.put("distance", distance);
    	result.put("params", getParams());
        //Write JSON file
        try {
        	FileWriter file = new FileWriter(path+"/test.json");
 
            file.write(result.toJSONString());
            file.flush();
            excutedAlgorithm("test");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ReadFileAndWriteToDB("test");
	 }
	private JSONObject getParams() {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//		String date = simpleDateFormat.format(new Date());
		 JSONObject params=new JSONObject();
		 params.put("cutMoocDuration", 1800);
		 params.put("linkMoocDuration", 1800);
		 params.put("unlinkEmptyContainerDuration", 1800);
		 params.put("unlinkLoadedContainerDuration", 1800);
		 params.put("linkEmptyContainerDuration", 1800);
		 params.put("linkLoadedContainerDuration", 1800);
		 params.put("hourPrev", 1);
		 params.put("hourPost", 1);
		 params.put("constraintDeliveryTime", true);
		 params.put("constraintWarehouseTractor", true);
		 params.put("constraintWarehouseDriver", true);
		 params.put("constraintWarehouseVendor", true);
		 params.put("constraintWarehouseBreaktimes", true);
		 params.put("constraintWarehouseHard", true);
		 params.put("constraintDriverBalance", true);
		 params.put("currentTime", simpleDateFormat.format(new Date()));
		 
		 return params;
	 }
	
}