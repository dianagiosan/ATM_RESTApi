package com.example.ATMProject;

import com.example.ATMProject.FeignClient.ATMClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import com.example.ATMProject.Application.DTO.ATMdto;

@SpringBootApplication
@EnableFeignClients
public class AtmProjectApplication {
	
	@Autowired
	ATMClient myClient;
	
	public static void main(String[] args) {
		SpringApplication.run(AtmProjectApplication.class, args);
	}
	
	public ATMdto transaction(int cashToWithdraw) {
		return myClient.transaction(cashToWithdraw);
	}
}