package com.springboot.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.model.BusSchedule;
import com.springboot.main.repository.BusScheduleRepository;
@Service
public class BusScheduleService {

	@Autowired
	private BusScheduleRepository busScheduleRepository;
	public BusSchedule insert(BusSchedule busSchedule) {
		return busScheduleRepository.save(busSchedule);
	}
	
	public List<BusSchedule> getBusByBusOperatorId(int boid) {
		return busScheduleRepository.findByBusOperatorId(boid);
	}


}