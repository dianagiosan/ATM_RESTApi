package com.example.ATMProject.Application.Service;

import com.example.ATMProject.Domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class TransactionList {
	public ArrayList<Transaction> transactions;
	public TransactionList() {
		transactions = new ArrayList<>();
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	
	
	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public void add(Transaction transaction){
		transactions.add(transaction);
		
	}
}
