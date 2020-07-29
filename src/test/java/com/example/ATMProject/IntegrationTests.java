package com.example.ATMProject;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static io.restassured.RestAssured.get;
import com.example.ATMProject.Application.DTO.ATMdto;

public class IntegrationTests {
	@Test
	public void withdraw101Test() {
		ATMdto output = get("http://localhost:8080/api/new-transaction?sum=101").as(ATMdto.class);
		Map<String, Integer> expected = new TreeMap<>();
		expected.put("ONEHUNDRED_RON(100)", 1);
		expected.put("ONE_RON(1)", 1);
		Assert.assertEquals(output.getBills(), expected);
		Assert.assertEquals(output.getMessage(), "Transaction approved");
	}
	
	@Test
	public void withdraw101and1000Test() {
		ATMdto output = get("http://localhost:8080/api/new-transaction?sum=101").as(ATMdto.class);
		Map<String, Integer> expected = new TreeMap<>();
		expected.put("ONEHUNDRED_RON(100)", 1);
		expected.put("ONE_RON(1)", 1);
		Assert.assertEquals(output.getBills(), expected);
		Assert.assertEquals(output.getMessage(), "Transaction approved");
		output = get("http://localhost:8080/api/new-transaction?sum=1000").as(ATMdto.class);
		expected.clear();
		expected.put("ONEHUNDRED_RON(100)", 10);
		Assert.assertEquals(output.getBills(), expected);
		Assert.assertEquals(output.getMessage(), "Transaction approved");
	}
	@Test
	public void withdrawsFromAdelina() {
		
	}
}