package com.example.ATMProject.Infrastructure;

import java.io.Serializable;
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
		Pattern p = Pattern.compile("-?\\d+");
		if (path.matches("(.*)transaction(.*)"))
			if (p.matcher(path).find())
				return "/api/new-transaction?sum=" + p.matcher(path).group(0);
		return "/api";
	}
}
