package com.example.ATMProject.Controllers;

import com.example.ATMProject.Application.Service.ATMService;
import com.example.ATMProject.Application.Service.ATMServiceImpl;
import com.example.ATMProject.Application.Service.MailService;
import com.example.ATMProject.Application.Service.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MailController {
	@Autowired
	ATMService ATM;
	@GetMapping("/inbox")
	public ResponseEntity<MailServiceImpl> returnMails() {
		MailServiceImpl output = ATM.getMailService();
		return new ResponseEntity<>(output, HttpStatus.OK);
	}
}
