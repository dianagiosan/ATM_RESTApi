package com.example.ATMProject.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ATMclient", url = "http://192.168.243.252:8080/")
public interface ATMClient {
	@GetMapping("/api/atm")
	String transaction(@RequestParam(value = "amount", defaultValue = "0") int cashToWithdraw);
}
