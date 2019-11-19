package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Distance;

@Repository
public interface IDistanceRepository extends IBaseRepository<Distance,String>{
	List<Distance> findBySrcCode(Integer srcCode);
}
