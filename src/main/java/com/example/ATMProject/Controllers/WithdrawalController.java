package com.example.ATMProject.Controllers;

import com.example.ATMProject.Application.DTO.ATMdto;
import com.example.ATMProject.Application.Service.ATMService;
import com.example.ATMProject.Config.MyFeatures;
import com.example.ATMProject.FeignClient.AdelinaClient;
import com.example.ATMProject.FeignClient.DragosClient;
import com.example.ATMProject.Infrastructure.NotEnoughCashLeftException;
import com.example.ATMProject.Infrastructure.TransactionNotPossibleException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;

@RestController
public class WithdrawalController {
	@Autowired
	ATMService ATMinstance;
	@Autowired
	DragosClient myDragosClient;
	@Autowired
	AdelinaClient myAdelinaClient;
	@HystrixCommand(fallbackMethod = "defaultMessage")
	@GetMapping("/new-transaction")
	public ResponseEntity<ATMdto> transaction(@RequestParam(value = "sum", defaultValue = "0") int cashToWithdraw) throws NotEnoughCashLeftException, TransactionNotPossibleException {
		/* Attempt to withdraw cash from this ATM */
		try {
			ATMdto attemptOutput = ATMinstance.splitIntoBills(cashToWithdraw);
			ObjectMapper o = new ObjectMapper();
			System.out.println(o.writeValueAsString(attemptOutput));
			return new ResponseEntity<>(attemptOutput, HttpStatus.OK);
		}
		/* If not possible, try to withdraw from the other available ATM */
		catch (Exception e1) {
			if (MyFeatures.WITHDRAW_DRAGOS.isActive()) {
				try {
					return new ResponseEntity<>(myDragosClient.transaction(cashToWithdraw), HttpStatus.OK);
				}
				catch (Exception e2) {
						return new ResponseEntity<>(myAdelinaClient.transaction(cashToWithdraw), HttpStatus.OK);
				}
			} else if (MyFeatures.WITHDRAW_ADELINA.isActive()) {
				try {
					return new ResponseEntity<>(myAdelinaClient.transaction(cashToWithdraw), HttpStatus.OK);
				}
				catch (Exception e3) {
					return new ResponseEntity<>(myDragosClient.transaction(cashToWithdraw), HttpStatus.OK);
				}
			}
			else {
				ATMdto attemptOutput = ATMinstance.splitIntoBills(cashToWithdraw);
				return new ResponseEntity<>(attemptOutput, HttpStatus.OK);
			}
		}
		
	}
	public ResponseEntity<ATMdto> defaultMessage(int cashToWithdraw){
		return new ResponseEntity<>(new ATMdto(new ArrayList<>(), "Transaction denied - something went wrong"), HttpStatus.SERVICE_UNAVAILABLE);
	}
}
