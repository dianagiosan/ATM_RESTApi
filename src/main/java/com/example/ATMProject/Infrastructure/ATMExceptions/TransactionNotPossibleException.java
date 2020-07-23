package com.example.ATMProject.Infrastructure.ATMExceptions;

/**
 * the exception that is thrown when there is not enough
 * cash left in the ATM in order to split the given sum into the
 * corresponding bills
 **/
public class TransactionNotPossibleException extends Exception {
	public TransactionNotPossibleException() {
		super("There are not enough bills in the ATM for the desired transaction. Try another transaction.");
	}
}
