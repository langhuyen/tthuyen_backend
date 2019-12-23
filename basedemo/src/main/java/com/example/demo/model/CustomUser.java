package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User{
	private static final long serialVersionUID = 1L;
public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

private String id;
private String email;
private String status;


public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

private static Collection<GrantedAuthority> getAuthorities(UserModel user){
	Collection<GrantedAuthority> grantedAuthorities=new ArrayList<>();
	if(user.getRole()!=null) {
		
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().getName()));
	}
	return grantedAuthorities;
}

public CustomUser(UserModel user) {
	super(user.getUsername(), user.getPassword(), getAuthorities(user));
	this.id=user.getId();
	this.email=user.getEmail();
	this.status=user.getStatus();
}
}
