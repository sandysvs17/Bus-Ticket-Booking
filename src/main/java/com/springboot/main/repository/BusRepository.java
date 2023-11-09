package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer>{

}