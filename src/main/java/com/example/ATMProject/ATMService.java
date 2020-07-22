package com.example.ATMProject;

/**
 * The ATMService base interface
 */
public interface ATMService {
	
	ATMOutput splitIntoBills(int cashToWithdraw);
	
}
