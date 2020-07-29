package com.example.ATMProject.Application.DTO;

import com.example.ATMProject.Domain.BillEntry;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * class that models the output the ATM provides the user with,
 * consisting of the bills and their respective amount, as well as a specific success/error message
 */
@Component
public class ATMdto {
	
	Map<String, Integer> bills = new TreeMap<>();
	
	String message;
	
	public ATMdto() {
	
	}
	
	public ATMdto(List<BillEntry> bills, String message) {
		for (BillEntry entry : bills) {
			this.bills.put(entry.billValueAsString(), entry.getBillAmount());
		}
		this.message = message;
	}
	
	public Map<String, Integer> getBills() {
		return bills;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ATMdto ATMdto = (ATMdto) o;
		return bills.equals(ATMdto.bills) &&
			message.equals(ATMdto.message);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bills, message);
	}
	
	public String toString() {
		return message + ". Here is your money: " + bills;
	}
}