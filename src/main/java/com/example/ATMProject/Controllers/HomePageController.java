package com.example.ATMProject.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {
	@GetMapping("/")
	public ResponseEntity<String> HelloMessage() {
		return new ResponseEntity<>("Hello! Transfer some money.", HttpStatus.OK);
		
	}
}
