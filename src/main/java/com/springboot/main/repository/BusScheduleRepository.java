package com.springboot.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.BusSchedule;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Integer>{

	List<BusSchedule> findByBusOperatorId(int boid);

}