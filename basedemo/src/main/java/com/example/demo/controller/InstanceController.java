package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Instance;

@RestController
@RequestMapping("/instance")
public class InstanceController extends BaseController<Instance, String> {
	

}
