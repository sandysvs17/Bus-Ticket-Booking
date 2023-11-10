package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.model.BusOperator;
import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.repository.BusOperatorRepository;
@Service
public class BusOperatorService {
	
	@Autowired
	private BusOperatorRepository busOperatorRepository;
	
	

	public BusOperator insert(BusOperator busOperator) {
		
		return busOperatorRepository.save(busOperator);
	}
	
	public BusOperator getById(int eid) throws InvalidIdException {
		Optional<BusOperator> optional = busOperatorRepository.findById(eid);
		if(!optional.isPresent())
			throw new InvalidIdException("eid invalid");
		return optional.get();
	}
	
	public List<BusOperator> getBusOperatorByExecutiveId(int eid) {
		return busOperatorRepository.findByExecutiveId(eid);
	}

	
}