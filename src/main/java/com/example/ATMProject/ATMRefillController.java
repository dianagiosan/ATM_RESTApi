package com.example.ATMProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ATMRefillController {
	@Autowired
	ATMService ATM;
	
	@RequestMapping(value = "/ATM-Refill", method = RequestMethod.PUT)
	public @ResponseBody
	void ATMRefill(@RequestParam(name = "para1") int billValue, @RequestParam(name = "para2") int billAmount) {
		ATM.ATMRefill(billValue, billAmount);
	}
}

