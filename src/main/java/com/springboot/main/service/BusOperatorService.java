package com.springboot.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.model.BusOperator;
import com.springboot.main.repository.BusOperatorRepository;
@Service
public class BusOperatorService {
	
	@Autowired
	private BusOperatorRepository busOperatorRepository;
	
	

	public BusOperator insert(BusOperator busOperator) {
		
		return busOperatorRepository.save(busOperator);
	}

	public List<BusOperator> getBusOperatorByExecutiveId(int eid) {
		return busOperatorRepository.findByExecutiveId(eid);
	}
}