package com.example.ATMProject.Application.Service;

import com.example.ATMProject.Application.DTO.ATMdto;
import com.example.ATMProject.Domain.BillEntry;

import java.util.List;

/**
 * The ATMService base interface
 */
public interface ATMService {
	void ATMRefill(int billValue, int billAmount);
	
	ATMdto splitIntoBills(int cashToWithdraw);
	
	List<BillEntry> getAvailableBills();
}
