package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomUser;
import com.example.demo.model.UserModel;
import com.example.demo.repository.IUserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	IUserRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserModel userEntity=null;
		try {
			userEntity=usersRepository.findByUsername(username);
			if (userEntity!=null&& userEntity.getEmail() != null && userEntity.getUsername()!=null) {
				CustomUser user=new CustomUser(userEntity);
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	}


