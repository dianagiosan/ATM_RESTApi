package com.example.ATMProject;

import com.example.ATMProject.client.ATMClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMController {
	@Autowired
	ATMService ATMinstance;
	@Autowired
	ATMClient myClient;
	
	@GetMapping("/new-transaction")
	public String transaction(@RequestParam(value = "sum", defaultValue = "0") int cashToWithdraw) {
		if (ATMinstance.splitIntoBills(cashToWithdraw).message.equals("Transaction approved"))
			return ATMinstance.splitIntoBills(cashToWithdraw).toString();
		else return myClient.transaction(cashToWithdraw);
	}
	
	@GetMapping("/")
	public String HelloMessage() {
		return "Hello! Transfer some money.";
		
	}
}
