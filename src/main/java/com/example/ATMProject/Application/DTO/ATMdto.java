package com.example.ATMProject.Application.DTO;

import com.example.ATMProject.Domain.BillEntry;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * class that models the output the ATM provides the user with,
 * consisting of the bills and their respective amount, as well as a specific success/error responseMessage
 */
public class ATMdto {
	
	Map<String, Integer> bills = new TreeMap<>();
	
	String responseMessage;
	
	public ATMdto() {
		super();
	}
	
	public ATMdto(List<BillEntry> bills, String responseMessage) {
		for (BillEntry entry : bills) {
			this.bills.put(entry.billValueAsString(), entry.getBillAmount());
		}
		this.responseMessage = responseMessage;
	}
	public Integer get1BillsAmount() {
		for(Map.Entry<String, Integer> entry : bills.entrySet()) {
			if(entry.getKey().equals("ONE_RON(1)"))
				return entry.getValue();
			
		}
		return 0;
	}
	public Integer get5BillsAmount() {
		for(Map.Entry<String, Integer> entry : bills.entrySet()) {
			if(entry.getKey().equals("FIVE_RON(5)"))
				return entry.getValue();
			
		}
		return 0;
	}
	public Integer get10BillsAmount() {
		for(Map.Entry<String, Integer> entry : bills.entrySet()) {
			if(entry.getKey().equals("TEN_RON(10)"))
				
				
				return entry.getValue();
			
		}
		
		return 0;
	}
	public Integer get50BillsAmount() {
		for(Map.Entry<String, Integer> entry : bills.entrySet()) {
			if(entry.getKey().equals("FIFTY_RON(50)"))
				return entry.getValue();
			
		}
		return 0;
	}
	public Integer get100BillsAmount() {
		for(Map.Entry<String, Integer> entry : bills.entrySet()) {
			if(entry.getKey().equals("ONEHUNDRED_RON(100)"))
				return entry.getValue();
			
		}
		return 0;
	}
	
	public Map<String, Integer> getBills() {
		return bills;
	}
	@JsonProperty("responseMessage")
	public String getMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public void setBills(Map<String, Integer> bills) {
		this.bills = bills;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ATMdto ATMdto = (ATMdto) o;
		return bills.equals(ATMdto.bills) &&
			responseMessage.equals(ATMdto.responseMessage);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bills, responseMessage);
	}
	
	public String toString() {
		return responseMessage + ". Here is your money: " + bills;
	}
}