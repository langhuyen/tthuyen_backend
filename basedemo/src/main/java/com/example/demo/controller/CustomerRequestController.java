package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomerRequest;
import com.example.demo.model.Entity;

@RestController
@RequestMapping("/CustomerRequest")
public class CustomerRequestController extends  BaseController<CustomerRequest, String > {

}
