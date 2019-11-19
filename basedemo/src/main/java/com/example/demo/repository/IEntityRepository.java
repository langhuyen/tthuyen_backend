package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Entity;

@Repository
public interface IEntityRepository extends IBaseRepository<Entity,String> {

}
