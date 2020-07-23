package com.example.ATMProject.ATMUtils;

/**
 * class for the mock emails sent with warnings that the ATM budget is running low
 */
public class MockMail {
	public static void send(String mail) {
		System.out.println("To : fillMeUpPlease@superbancomat.com Contents: " + mail);
	}
}