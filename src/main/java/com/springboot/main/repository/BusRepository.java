package com.springboot.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer>{

	List<Bus> findByBusOperatorId(int boid);

	List<Bus> findByExecutiveId(int eid);

}