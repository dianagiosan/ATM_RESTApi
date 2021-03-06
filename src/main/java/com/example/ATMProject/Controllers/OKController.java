package com.example.ATMProject.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OKController {
	@GetMapping("/OK")
	public ResponseEntity<String> OKResponse() {
		return new ResponseEntity<>("I'm up and running.", HttpStatus.OK);
	}
}
