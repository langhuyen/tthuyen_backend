package com.example.demo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="contaniner_type")
public class ContainerType {
	 	@Id
		private String id;
		private String code;
		private String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		

}
