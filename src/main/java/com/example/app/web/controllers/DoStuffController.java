package com.example.app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.services.MessageProducingService;

@RestController
public class DoStuffController {
	@Autowired MessageProducingService service;
	
	
	@GetMapping("/doStuff")
	public Object doStuff() {
		return service.doStuff();
	}
}
