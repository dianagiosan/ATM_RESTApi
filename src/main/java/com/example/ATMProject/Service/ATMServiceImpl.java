package com.example.ATMProject.Service;

import com.example.ATMProject.ATMUtils.BillEntry;
import com.example.ATMProject.ATMUtils.MockMail;
import com.example.ATMProject.ATMUtils.NotEnoughCashLeftException;
import com.example.ATMProject.ATMUtils.TransactionNotPossibleException;
import com.example.ATMProject.DTO.ATMOutput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * main class that models the ATM functionality
 */
@Service
public class ATMServiceImpl implements ATMService {
	/**
	 * in order to avoid sending the warning mail twice for the same event
	 */
	boolean critical100MessageSent = false;
	boolean warning100MessageSent = false;
	boolean warning50MessageSent = false;
	/**
	 * the list of available bills in the ATM
	 */
	private java.util.List<BillEntry> availableBills = new ArrayList<>();
	
	/**
	 * initialize the available bills every time an ATM object is created
	 */
	public ATMServiceImpl() {
		availableBills.add(new BillEntry(100, 50));
		availableBills.add(new BillEntry(50, 50));
		availableBills.add(new BillEntry(10, 100));
		availableBills.add(new BillEntry(5, 100));
		availableBills.add(new BillEntry(1, 100));
		
	}
	
	public List<BillEntry> getAvailableBills() {
		return availableBills;
	}
	
	public void ATMRefill(int billValue, int billAmount) {
		for (BillEntry b : availableBills) {
			if (b.getBillValue() == billValue)
				b.setBillAmount(b.getBillAmount() + billAmount);
		}
		
	}
	
	/**
	 * method that splits the desired sum of money into corresponding bills
	 *
	 * @param cashToWithdraw - the sum that the user desires to withdraw
	 * @return ATMOutput object : list of billEntries and success/error message
	 */
	public ATMOutput splitIntoBills(int cashToWithdraw) {
		/*
		  sort the list of available bills descendingly by their value
		 */
		availableBills.sort((BillEntry1, BillEntry2) -> BillEntry2.getBillValue() - BillEntry1.getBillValue());
		
		/*
		  the current bill in the list that we try to include in the output
		 */
		int currentBillIndex = 0;
		
		/*
		  how many bills like the current one were we able to include so far?
		 */
		int currentBillCounter;
		List<BillEntry> returnBills = new ArrayList<>();
		String message;
		boolean OKtoContinue = false;
		for (BillEntry entry : availableBills) {
			/* first, check to see if the ATM is not empty */
			
			if (entry.getBillAmount() != 0) {
				OKtoContinue = true;
				break;
			}
		}
		if (!OKtoContinue)
			try {
				/* throw exception, there's no more cash in the ATM */
				
				throw new NotEnoughCashLeftException();
			} catch (NotEnoughCashLeftException e) {
				System.out.println(e.getMessage());
				message = "Transaction denied : The ATM ran out of cash. Please come back later.";
				returnBills.add(new BillEntry(0, 0)); /* no money outputted */
				return new ATMOutput(returnBills, message);
			}
		List<BillEntry> availableBillsCopy = new ArrayList<>(); /* create a working copy of the ATM bills */
		for (BillEntry entry : availableBills) {
			BillEntry entryCopy = new BillEntry(entry.getBillValue(), entry.getBillAmount());
			availableBillsCopy.add(entryCopy);
		}
		/* we still have to withdraw cash */
		while (cashToWithdraw > 0 && currentBillIndex < availableBillsCopy.size()) {
			currentBillCounter = 0;
			/* try to include one more of the current bill */
			while (availableBillsCopy.get(currentBillIndex).getBillAmount() > 0 &&
				cashToWithdraw >= availableBillsCopy.get(currentBillIndex).getBillValue()) {
				/* decrease the goal withdrawal sum */
				cashToWithdraw -= availableBillsCopy.get(currentBillIndex).getBillValue();
				/* lower the amount of the current bill in the list */
				availableBillsCopy.get(currentBillIndex).decreaseAmount();
				/* we've added one more of the current bill */
				currentBillCounter++;
			}
			/*
			 * we have to add a new bill to the output, with it's corresponding amount
			 */
			if (currentBillCounter > 0)
				returnBills.add(new BillEntry(availableBills.get(currentBillIndex).getBillValue(), currentBillCounter));
			currentBillIndex++;
			
		}
		
		/*
		 * if the cash could not be withdrawn, it's because the
		 * bill configuration in the ATM doesn't allow it
		 */
		if (cashToWithdraw > 0) {
			message = "Transaction denied : There are not enough bills in the ATM for the desired transaction. Try another transaction.";
			returnBills.clear();
			/* no money outputted */
			returnBills.add(new BillEntry(0, 0));
			try {
				/* throw exception */
				throw new TransactionNotPossibleException();
			} catch (TransactionNotPossibleException e) {
				System.out.println(e.getMessage());
			}
		} else {
			/*
			 * if the transaction was successful, update the new bill configuration
			 * in the ATM and send success message
			 */
			message = "Transaction approved";
			availableBills.clear();
			availableBills.addAll(availableBillsCopy);
			//check to see if we have to send a warning mail
			if (availableBills.get(0).getBillAmount() < 5 && !critical100MessageSent) {
				MockMail.send("Number of 100 bills is CRITICAL.");
				critical100MessageSent = true;
			} else if (availableBills.get(0).getBillAmount() < 10 && !warning100MessageSent) {
				MockMail.send("Warning. Number of 100 bills under 20%.");
				warning100MessageSent = true;
			}
			if (availableBills.get(1).getBillAmount() <= 7 && !warning50MessageSent) {
				
				MockMail.send("Warning. Number of 50 bills is under 15%.");
				warning50MessageSent = true;
			}
		}
		return new ATMOutput(returnBills, message);
	}
	
	
}