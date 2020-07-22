package com.example.ATMProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceCheckController {
	@Autowired
	ATMService ATM;
	@GetMapping("/api/check-balance")
	public String checkBalance(){
		return ATM.getAvailableBills().toString();
	}
}