package com.example.ATMProject.Application.Service;

import com.example.ATMProject.Domain.MockMail;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class MailServiceImpl implements MailService{
	List<MockMail> inbox = new ArrayList<>();
	@Override
	public void send(String mailContents) throws JRException {
		
		MockMail.send(mailContents, inbox);
	}
	public String toString(){
		return inbox + "";
	}
	public List<MockMail> getInbox(){
		return inbox;
	}
}
