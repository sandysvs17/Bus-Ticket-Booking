package com.springboot.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.BusOperator;

public interface BusOperatorRepository extends JpaRepository<BusOperator, Integer>{
	
	List<BusOperator> findByExecutiveId(int eid);
	
}