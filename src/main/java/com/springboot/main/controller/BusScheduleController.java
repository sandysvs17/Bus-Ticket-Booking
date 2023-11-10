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
import com.springboot.main.model.BusSchedule;
import com.springboot.main.service.BusOperatorService;
import com.springboot.main.service.BusScheduleService;
import com.springboot.main.service.BusService;

@RestController
@RequestMapping("/busSchedule")
public class BusScheduleController {

	@Autowired
	private BusScheduleService busScheduleService;

	@Autowired
	private BusService busService;

	@Autowired
	private BusOperatorService busOperatorService;



	@PostMapping("/add/{bid}/{boid}")
	public ResponseEntity<?> assignBus (@PathVariable("boid")int boid,@PathVariable("bid")int bid,
			@RequestBody BusSchedule busSchedule){
		try {
			BusOperator busOperator=busOperatorService.getById(boid);
			Bus bus=busService.getById(bid);
			busSchedule.setBusOperator(busOperator);
			busSchedule.setBus(bus);
			busSchedule=busScheduleService.insert(busSchedule);
			return ResponseEntity.ok().body(busSchedule);
		}
		catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}