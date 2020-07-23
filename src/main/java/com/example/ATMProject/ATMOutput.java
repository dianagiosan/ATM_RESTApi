package com.example.ATMProject;

import java.util.*;
import java.util.List;

/**
 * class that models the output the ATM provides the user with,
 * consisting of the bills and their respective amount, as well as a specific success/error message
 */

public class ATMOutput {
	
	Map<String, Integer> bills = new TreeMap<>();
	
	String message;
	public ATMOutput(){
	
	}
	public ATMOutput(List<BillEntry> bills, String message) {
		for(BillEntry entry : bills) {
			this.bills.put(entry.billValueAsString(), entry.getBillAmount());
		}
		this.message = message;
	}
	
	public Map <String, Integer> getBills() {
		return bills;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ATMOutput atmOutput = (ATMOutput) o;
		return bills.equals(atmOutput.bills) &&
			message.equals(atmOutput.message);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bills, message);
	}
	
	public String toString() {
		return message + ". Here is your money: " + bills;
	}
}