package com.example.ATMProject;

import java.util.List;

/**
 * The ATMService base interface
 */
public interface ATMService {
	void ATMRefill(int billValue, int billAmount);
	ATMOutput splitIntoBills(int cashToWithdraw);
	
	List<BillEntry> getAvailableBills();
}
