package com.example.ATMProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AtmProjectApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(AtmProjectApplication.class, args);
	}
	
}