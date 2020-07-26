package com.example.ATMProject.FeignClient;

import com.example.ATMProject.Application.DTO.ATMdto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "DragosClient", url = "http://192.168.243.253:8080/")
public interface DragosClient {
	@GetMapping("/transaction")
	ATMdto transaction(@RequestParam(defaultValue = "0") int cashToWithdraw);
}
