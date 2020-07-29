package com.example.ATMProject.Controllers;

import com.example.ATMProject.Application.Service.ATMService;
import com.example.ATMProject.Domain.BillEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceCheckController {
	@Autowired
	ATMService ATM;
	
	@GetMapping("/check-balance")
	public ResponseEntity<List<BillEntry>> checkBalance() {
		return new ResponseEntity<>(ATM.getAvailableBills(), HttpStatus.OK);
	}
}
