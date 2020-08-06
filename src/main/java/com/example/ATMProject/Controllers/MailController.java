package com.example.ATMProject.Controllers;

import com.example.ATMProject.Application.Service.ATMService;
import com.example.ATMProject.Application.Service.MailServiceImpl;
import com.example.ATMProject.Application.Service.TransactionList;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
	@Autowired
	ATMService ATM;
	@Autowired
	TransactionList transactionList;
	@GetMapping("/inbox")
	public ResponseEntity<MailServiceImpl> returnMails() throws JRException {
		MailServiceImpl output = ATM.getMailService();
		return new ResponseEntity<>(output, HttpStatus.OK);
	}
}
