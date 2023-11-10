package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Bus;
import com.springboot.main.model.BusOperator;
import com.springboot.main.model.BusSchedule;
import com.springboot.main.repository.BusRepository;

@Service
public class BusService {

	@Autowired 
	private BusRepository busRepository;
	public Bus insert(Bus bus) {
		return busRepository.save(bus);
	}
	
	public List<Bus> getBusByBusOperatorId(int boid) {
		return busRepository.findByBusOperatorId(boid);
	}

	public List<Bus> getBusByExecutiveId(int eid) {
		return busRepository.findByExecutiveId(eid);
	}
	
	public Bus getById(int bid) throws InvalidIdException {
		Optional<Bus>optional=busRepository.findById(bid);
		if(!optional.isPresent())
			throw new InvalidIdException("bid is incorrect");
		return optional.get();
	}

}