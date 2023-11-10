package com.springboot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Bus;
import com.springboot.main.model.BusOperator;
import com.springboot.main.model.BusSchedule;
import com.springboot.main.model.Executive;
import com.springboot.main.model.User;
import com.springboot.main.service.BusOperatorService;
import com.springboot.main.service.BusScheduleService;
import com.springboot.main.service.BusService;
import com.springboot.main.service.ExecutiveService;
import com.springboot.main.service.UserService;

@RestController
@RequestMapping("/busOperator")
public class BusOperatorController {

	@Autowired
	private BusOperatorService busOperatorService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExecutiveService executiveService;
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private BusScheduleService busScheduleService;
	
	@PostMapping("/add/{eid}")
	public ResponseEntity<?> assignBusOperator(@PathVariable("eid") int eid,@RequestBody BusOperator busOperator) {
	
		try {
		Executive executive=executiveService.getById(eid);
		
		busOperator.setExecutive(executive);
		User user=busOperator.getUser();
		String passwordPlain = user.getPassword();
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		user.setRole("BUSOPERATOR");
		user = userService.insert(user);
		busOperator.setUser(user);
		
		busOperator=busOperatorService.insert(busOperator);
		return ResponseEntity.ok().body(busOperator);
		}catch(InvalidIdException e){
			return ResponseEntity.badRequest().body(e.getMessage());

		}
				
	}
	
	@GetMapping("/get/bus/{boid}")
	public List<Bus>getBusByBusOperatorId(@PathVariable("boid")int boid){
		return busService.getBusByBusOperatorId(boid);
	}
	

	@GetMapping("/get/busSchedule/{boid}")
	public List<BusSchedule> getBusScheduleByBusOperatorId(@PathVariable("boid") int boid){

		return busScheduleService.getBusByBusOperatorId(boid); 
	}
	
	
	
}