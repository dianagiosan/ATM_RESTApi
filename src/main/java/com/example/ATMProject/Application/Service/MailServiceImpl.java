package com.example.ATMProject.Application.Service;

import com.example.ATMProject.Domain.MockMail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailServiceImpl implements MailService{
	List<MockMail> inbox = new ArrayList<>();
	@Override
	public void send(String mailContents) {
		MockMail.send(mailContents, inbox);
	}
	public String toString(){
		return inbox + "";
	}
	public List<MockMail> getInbox(){
		return inbox;
	}
}
