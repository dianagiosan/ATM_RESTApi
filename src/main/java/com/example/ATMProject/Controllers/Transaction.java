package com.example.ATMProject.Controllers;

import com.example.ATMProject.ReportEntry;

import java.time.LocalDateTime;

public class Transaction {
	LocalDateTime timestamp;
	ReportEntry reportEntry;
	public Transaction(LocalDateTime timestamp, ReportEntry reportEntry) {
		this.timestamp = timestamp;
		this.reportEntry = reportEntry;
	}
}
