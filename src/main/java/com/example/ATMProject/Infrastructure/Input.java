package com.example.ATMProject.Infrastructure;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Input implements Serializable {
	
	public String path;
	
	
	public Input(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String newPath() {
		if (path.matches("(.*)transact(.*)"))
				return "/api/new-transaction?sum=" + path.replaceAll("\\D+","");
		else if(path.matches("(.*)balance(.*)") || path.matches("(.*)how much(.*)"))
			return "/api/check-balance";
		else if(path.matches("(.*)report(.*)"))
			return "/api/report";
		else return "/api/FormInputError";
	}
}
