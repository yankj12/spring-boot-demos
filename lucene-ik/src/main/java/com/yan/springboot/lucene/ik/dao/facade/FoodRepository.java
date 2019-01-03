package com.yan.springboot.lucene.ik.dao.facade;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yan.springboot.lucene.ik.schema.Food;

public interface FoodRepository extends JpaRepository<Food,Integer>{

}
