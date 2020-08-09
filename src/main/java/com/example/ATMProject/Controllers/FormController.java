package com.example.ATMProject.Controllers;

import com.example.ATMProject.Infrastructure.Input;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FormController {
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String show(Input input) {
		return "form";
	}
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public @ResponseBody RedirectView show(@ModelAttribute("input") Input inp, BindingResult bindingResult) {
		String newPath = inp.newPath();
		return new RedirectView(newPath);
	}
}
