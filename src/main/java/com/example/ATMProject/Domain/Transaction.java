package com.example.ATMProject.Domain;

import com.example.ATMProject.ReportEntry;

import java.time.LocalDateTime;

public class Transaction {
	public LocalDateTime timestamp;
	public ReportEntry reportEntry;
	public Transaction(LocalDateTime timestamp, ReportEntry reportEntry) {
		this.timestamp = timestamp;
		this.reportEntry = reportEntry;
	}
}
