package com.example.demo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="contaniner_type")
public class ContainerType extends BaseModel {
	 	@Id
		private String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
		

}
