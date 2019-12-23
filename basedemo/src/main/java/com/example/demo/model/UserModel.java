package com.example.demo.model;




import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="user")
public class UserModel {
	
	@Id
	private String id;
	

	private String username;
	
	
	private String password;
	

	private String lastname;
	

	private String avatar;
	

	private String firstname;
	

	private String email;
	
	
	private Date createdDate=new Date();
	
	private String status="UNVERIFY";
	

	private String tokenEmail;
	private RoleModel role=new RoleModel("admin","admin");

	
	
	public RoleModel getRole() {
		return role;
	}


	public void setRole(RoleModel role) {
		this.role = role;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getTokenEmail() {
		return tokenEmail;
	}


	public void setTokenEmail(String tokenEmail) {
		this.tokenEmail = tokenEmail;
	}
	
	

}
