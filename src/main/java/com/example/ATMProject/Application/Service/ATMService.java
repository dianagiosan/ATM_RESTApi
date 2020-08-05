package com.example.ATMProject.Application.Service;

import com.example.ATMProject.Application.DTO.ATMdto;
import com.example.ATMProject.Domain.BillEntry;
import com.example.ATMProject.Infrastructure.Exceptions.NotEnoughCashLeftException;
import com.example.ATMProject.Infrastructure.Exceptions.TransactionNotPossibleException;
import net.sf.jasperreports.engine.JRException;

import java.util.List;

/**
 * The ATMService base interface
 */
public interface ATMService {
	void ATMRefill(int billValue, int billAmount);
	
	ATMdto splitIntoBills(int cashToWithdraw) throws NotEnoughCashLeftException, TransactionNotPossibleException, JRException;
	
	List<BillEntry> getAvailableBills();
	
	MailServiceImpl getMailService();
}
