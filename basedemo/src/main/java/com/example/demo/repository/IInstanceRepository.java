package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Instance;

@Repository
public interface IInstanceRepository extends IBaseRepository<Instance,String> {

}
