package com.example.ATMProject;

import com.example.ATMProject.client.ATMClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithdrawalController {
	@Autowired
	ATMService ATMinstance;
	@Autowired
	ATMClient myClient;
	
	@GetMapping("/api/new-transaction")
	public String transaction(@RequestParam(value = "sum", defaultValue = "0") int cashToWithdraw) {
		/* Attempt to withdraw cash from this ATM */
		ATMOutput attemptOutput = ATMinstance.splitIntoBills(cashToWithdraw);
		if (attemptOutput.message.equals("Transaction approved"))
			return attemptOutput.toString();
			/* If not possible, try to withdraw from the other available ATM */
		else return myClient.transaction(cashToWithdraw);
	}
	
}
