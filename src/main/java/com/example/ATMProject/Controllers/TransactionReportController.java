package com.example.ATMProject.Controllers;

import com.example.ATMProject.Application.Service.TransactionList;
import com.example.ATMProject.Domain.Transaction;
import com.example.ATMProject.ReportEntry;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TransactionReportController {
	@Autowired
	TransactionList transactionList;
	@GetMapping("/transaction-report")
	public ResponseEntity<String> transactionReport(@RequestParam(value = "minutes", defaultValue = "0") int minutes, HttpServletResponse response) throws JRException, IOException {
		ArrayList<ReportEntry> list = new ArrayList<>();
		list.add(new ReportEntry(1, 2, 3, 4, 5));
		for(Transaction t : transactionList.getTransactions()) {
			if(ChronoUnit.MINUTES.between(t.timestamp, LocalDateTime.now()) <= minutes)
				list.add(t.reportEntry);
		}
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(list);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("BeanCollection", jrBeanCollectionDataSource);
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		JasperDesign design= JRXmlLoader.load(classLoader.getResourceAsStream("ATM.jrxml"));//file is your .jrxml file or .jasper file
		JasperReport report = JasperCompileManager.compileReport(design);
		JasperPrint print= JasperFillManager.fillReport(report,parameters, jrBeanCollectionDataSource);
		response.setContentType("application/x-pdf");
		response.addHeader("Content-disposition", "attachment; filename = index.pdf");
		JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
		return new ResponseEntity<>("PDF downloaded successfully!", HttpStatus.OK);
	}}
