package com.example.ATMProject.Presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {
	@GetMapping("/")
	public String HelloMessage() {
		return "Hello! Transfer some money.";
		
	}
}
