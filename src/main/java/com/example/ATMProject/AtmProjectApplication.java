package com.example.ATMProject;

import com.example.ATMProject.client.ATMClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import springfox.documentation.spring.web.json.Json;

@SpringBootApplication
@EnableFeignClients
public class AtmProjectApplication {
	
	@Autowired
	ATMClient myClient;
	
	public static void main(String[] args) {
		SpringApplication.run(AtmProjectApplication.class, args);
	}
	
	public ATMOutput transaction(int cashToWithdraw) {
		return myClient.transaction(cashToWithdraw);
	}
}