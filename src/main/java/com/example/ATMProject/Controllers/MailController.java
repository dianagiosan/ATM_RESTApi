package com.example.ATMProject.Controllers;

import com.example.ATMProject.Application.Service.ATMService;
import com.example.ATMProject.Application.Service.MailServiceImpl;
import com.example.ATMProject.ReportEntry;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
