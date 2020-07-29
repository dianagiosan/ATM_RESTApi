package com.example.ATMProject.FeignClient;

import com.example.ATMProject.Application.DTO.ATMdto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "AdelinaClient", url = "${adelina.atm}")
public interface AdelinaClient extends FeignClientInterface {
	@GetMapping("/api/new-transaction")
	ATMdto transaction(@RequestParam(defaultValue = "0") int sum);
}
