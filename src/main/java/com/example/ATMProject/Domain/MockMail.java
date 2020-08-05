package com.example.ATMProject.Domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 * class for the mock emails sent with warnings that the ATM budget is running low
 */
public class MockMail {
	public static final String preamble = "To : fillMeUpPlease@superbancomat.com Contents: ";
	public String mailContents;
	public LocalDateTime timestamp;
	public MockMail(String mailContents) {
		this.mailContents = mailContents;
		timestamp = LocalDateTime.now();
	}
	public static void send(String mail, List<MockMail> inbox) {
		inbox.add(new MockMail(mail));
	}
	public String toString() {
		return "Sent at " + timestamp + " " + preamble + "" + mailContents;
	}
}