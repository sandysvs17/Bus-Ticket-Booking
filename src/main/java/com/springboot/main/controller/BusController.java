package com.springboot.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Bus;
import com.springboot.main.model.BusOperator;
import com.springboot.main.model.Executive;
import com.springboot.main.service.BusOperatorService;
import com.springboot.main.service.BusService;
import com.springboot.main.service.ExecutiveService;

@RestController
@RequestMapping("/bus")
public class BusController {

	@Autowired
	private BusService busService;
	
	@Autowired
	private ExecutiveService executiveService;
	
	@Autowired
	private BusOperatorService busOperatorService;
	@PostMapping("/add/{boid}/{eid}")
	public ResponseEntity<?> assignBus (@PathVariable("boid")int boid,@PathVariable("eid") int eid,
			@RequestBody Bus bus){
		try {
			BusOperator busOperator=busOperatorService.getById(boid);
			Executive executive = executiveService.getById(eid);
			bus.setBusOperator(busOperator);
			bus.setExecutive(executive);
			bus=busService.insert(bus);
			return ResponseEntity.ok().body(bus);
		}
		catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
}
}