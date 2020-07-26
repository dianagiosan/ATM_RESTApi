package com.example.ATMProject.FeignClient;

import com.example.ATMProject.Application.DTO.ATMdto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "AdelinaClient", url = "http://192.168.243.252:8080/")
public interface AdelinaClient {
	@GetMapping("/api/atm")
	ATMdto transaction(@RequestParam(value = "amount", defaultValue = "0") int cashToWithdraw);
}
