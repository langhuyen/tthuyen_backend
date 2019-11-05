package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Distance;

@RestController
@RequestMapping("/Distance")
public class DistanceController extends BaseController<Distance, String> {

}
