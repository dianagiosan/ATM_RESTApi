package com.example.ATMProject.Application.Service;

import net.sf.jasperreports.engine.JRException;

public interface MailService {
	
	
	 void send(String mailContents) throws JRException;
}
