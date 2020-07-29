package com.example.ATMProject.Controllers;

import com.example.ATMProject.Application.Service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ATMRefillController {
	@Autowired
	ATMService ATM;
	
	@RequestMapping(value = "/ATM-Refill", method = RequestMethod.POST)
	public @ResponseBody
	void ATMRefill(@RequestParam(name = "Value") int billValue, @RequestParam(name = "Amount") int billAmount) {
		ATM.ATMRefill(billValue, billAmount);
	}
}

