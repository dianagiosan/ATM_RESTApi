package com.example.ATMProject.FeignClient;

import com.example.ATMProject.Application.DTO.ATMdto;

public interface FeignClientInterface {
	ATMdto transaction(int cashToWithdraw);
}
