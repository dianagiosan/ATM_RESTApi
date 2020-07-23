package com.example.ATMProject.Service;

import com.example.ATMProject.ATMUtils.BillEntry;
import com.example.ATMProject.DTO.ATMOutput;

import java.util.List;

/**
 * The ATMService base interface
 */
public interface ATMService {
	void ATMRefill(int billValue, int billAmount);
	
	ATMOutput splitIntoBills(int cashToWithdraw);
	
	List<BillEntry> getAvailableBills();
}
