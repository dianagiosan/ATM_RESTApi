package com.example.ATMProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMController {
	@Autowired
	ATMService ATMinstance;
	@GetMapping("/new-transaction")
	public ATMOutput transaction(@RequestParam(value = "sum", defaultValue = "0") int cashToWithdraw){
		return ATMinstance.splitIntoBills(cashToWithdraw);
	}
	@GetMapping("/")
	public String HelloMessage(){
		return "Hello! Transfer some money.";
		
	}
}
