package com.example.ATMProject.client;

import com.example.ATMProject.ATMOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ATMclient", url = "http://192.168.243.15:8080/")
public interface ATMClient {
	@GetMapping("/transaction")
	ATMOutput transaction(@RequestParam(value = "amount", defaultValue = "0") int cashToWithdraw);
}
