package com.example.ATMProject.Presentation;

import com.example.ATMProject.FeignClient.ATMClient;
import com.example.ATMProject.Application.DTO.ATMdto;
import com.example.ATMProject.Application.Service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<ATMdto> transaction(@RequestParam(value = "sum", defaultValue = "0") int cashToWithdraw) {
		/* Attempt to withdraw cash from this ATM */
		ATMdto attemptOutput = ATMinstance.splitIntoBills(cashToWithdraw);
		if (attemptOutput.getMessage().equals("Transaction approved"))
			return new ResponseEntity<>(attemptOutput, HttpStatus.OK);
			/* If not possible, try to withdraw from the other available ATM */
		else return new ResponseEntity<>(myClient.transaction(cashToWithdraw), HttpStatus.OK);
	}
	
}