package com.springboot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Bus;
import com.springboot.main.model.BusOperator;
import com.springboot.main.model.Executive;
import com.springboot.main.model.User;
import com.springboot.main.service.BusOperatorService;
import com.springboot.main.service.BusService;
import com.springboot.main.service.ExecutiveService;
import com.springboot.main.service.UserService;

@RestController
@RequestMapping("/executive")
public class ExecutiveController {
	
	@Autowired
	private ExecutiveService executiveService;
	
	@Autowired
	private BusOperatorService busOperatorService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BusService busService;
	
	@PostMapping("/add")
	public Executive insertExecutive(@RequestBody Executive executive) {
		User user=executive.getUser();
		String passwordPlain = user.getPassword();
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		user.setRole("EXECUTIVE");
		user = userService.insert(user);
		executive.setUser(user);
		
		
		return executiveService.insert(executive);
	}
	
	@GetMapping("/get/busOperator/{eid}")
	public List<BusOperator>getBusOperatorByExecutiveId(@PathVariable("eid")int eid){
		return busOperatorService.getBusOperatorByExecutiveId(eid);
	}
	
	@GetMapping("/get/bus/{eid}")
	public List<Bus>getBusByExecutiveId(@PathVariable("eid")int eid){
		return busService.getBusByExecutiveId(eid);
	}
	
}