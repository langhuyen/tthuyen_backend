package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.GenerateCode;
import com.example.demo.model.ResponseModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.IGenerareCodeRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.GenerateCodeService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserRepository userRepository;
	@Autowired
	EmailService emailService;
	@Autowired
	GenerateCodeService generateService;
	@Autowired
	PasswordEncoder passwordEncoder;

	//Confirm email
	
	//Gửi lại mã confirm 
	@PostMapping(value="/sendTokenEmail")
	public void sendTokenEmail(@Param(value="userId") String userId) {
		UserModel user=userRepository.findById(userId).get();
		if(user!=null) {
			sendEmail(user);
		}
	}
	
	@RequestMapping(value = "/verifyUser", method = RequestMethod.POST)
	public ResponseModel verifyUser(@Param(value="id") String id,@Param(value="tokenEmail") String tokenEmail) {
		ResponseModel res = new ResponseModel();
		UserModel user=userRepository.findById(id).get();
		if(user==null) {
			res.notFound();
			return res;
		}
		if(user.getTokenEmail().equals(tokenEmail)){
			user.setStatus("VERIFY");
			userRepository.save(user);
			res.setData( Arrays.asList(true));
			//Tao luong sinh du lieu
			initData(id);
		}else {
			res.setData( Arrays.asList(false));
		}
		return res;
	}
	private void initData(String userId){
		  new Thread(new Runnable() { 
	            public void run() 
	            { 
	            	//Khai bao 1 mang
	            	List<String> arrType=Arrays.asList("CONTAINER","PORT","WAREHOUSE","DEPOTCONTAINER","DEPOTTRUCK",
	            			"DEPOTTRAILLER","TRUCK","MOOC","ImportLadenRequest","ImportEmptyRequest","ExportEmptyRequest","ExportLadenRequest");
	            	List<String> arrPrefix=Arrays.asList("C","P","W","DC","DTRU","DTRA","T","M","ILR","IER","EER","ELR");
	            	for (int i = 0; i < arrType.size(); i++) {
						GenerateCode generateCode=new GenerateCode();
						generateCode.setCode("1");
						generateCode.setType(arrType.get(i));
						generateCode.setPrefix(arrPrefix.get(i));
						generateCode.setUserId(userId);
						generateService.insertEntity(generateCode);
					}
	            	
	            	
	            }}).start();
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)

	public ResponseModel register(@RequestBody UserModel user) {
		ResponseModel res = new ResponseModel();
		Map<String, String> errObject = new HashMap<String, String>();
		if (user.getEmail() == null) {
			errObject.put("validation", "Bạn chưa có email!Vui lòng nhập email");
			List<Object> data = new ArrayList<Object>();
			data.add(errObject);
			res.setData(data);
			res.error();
			return res;
		}
		if (user.getUsername() == null) {
			errObject.put("validation", "Bạn chưa có username!Vui lòng nhập username");
			List<Object> data = new ArrayList<Object>();
			data.add(errObject);
			res.setData(data);
			res.error();
			return res;
		}
		if (user.getPassword() == null) {
			errObject.put("validation", "Bạn chưa có password!Vui lòng nhập password");
			List<Object> data = new ArrayList<Object>();
			data.add(errObject);
			res.setData(data);
			res.error();
			return res;
		}

		if (userRepository.existsByUsername(user.getUsername()) == true
				|| userRepository.existsByEmail(user.getEmail()) == true) {
			errObject.put("validation", "Đã tồn tại tài khoản");
			List<Object> data = new ArrayList<Object>();
			data.add(errObject);
			res.setData(data);
			res.error();
			return res;
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			userRepository.insert(user);
		} catch (Exception e) {
			errObject.put("validation", "Có lỗi xảy ra");
			List<Object> data = new ArrayList<Object>();
			data.add(errObject);
			res.setData(data);
			res.error();
			return res;
		}
//		errObject.put("success", "Thêm tài khoản thành công");
		sendEmail(user);
		List<Object> data = new ArrayList<Object>();
		data.add(user);
		res.setData(data);
		return res;
	}
	
	public void sendEmail(UserModel user) {
		String token=ramdomToken();
		user.setTokenEmail(token);
		userRepository.save(user);
		emailService.sendEmailTo(user.getEmail(),token,user.getUsername());
		
	}
	public String ramdomToken() {
		StringBuilder result=new StringBuilder();
		for(int i=0;i<6;i++) {
			Random rd=new Random();
			result.append(rd.nextInt(10));
		}
		return result.toString() ;
	}

}
