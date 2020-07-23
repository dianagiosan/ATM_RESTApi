package com.example.ATMProject.Infrastructure.ATMExceptions;

/**
 * The ATM throws an exception when it completely runs out of cash
 */
public class NotEnoughCashLeftException extends Exception {
	public NotEnoughCashLeftException() {
		super("The ATM ran out of cash. Please come back later.");
	}
}