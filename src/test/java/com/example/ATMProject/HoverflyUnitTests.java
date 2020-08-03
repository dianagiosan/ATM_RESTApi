package com.example.ATMProject;

import com.example.ATMProject.Application.DTO.ATMdto;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.badRequest;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static org.hamcrest.Matchers.equalTo;

@Component
public class HoverflyUnitTests {
	
	TestRestTemplate ATMtemplate = new TestRestTemplate();
	
	@Rule
	public static HoverflyRule hoverflyRule1 = HoverflyRule.inSimulationMode(dsl(
		service("localhost:8080/api")
			.get("/OK")
			.willReturn(success("Success", "text/plain")).
			get("/new-transaction?sum=1000")
			.willReturn(success("{\"bills\":{\"ONEHUNDRED_RON(100)\":10},\"message\":\"Transaction approved - money from Diana\"}", "application/json"))
			.get("/notHere")
			.willReturn(badRequest())));
	
	@Test
	public void testOKPage() {
		
		given().
			when().
			get("http://localhost:8080/api/OK").
			then().
			assertThat().
			statusCode(200).
			and().
			body(equalTo("I'm up and running."));
	}
	
	@Test
	public void testBadRequest() {
		
		given().
			when().
			get("http://localhost:8080/api/notHere").
			then().
			assertThat().
			statusCode(404);
	}
	
	
	@Test
	public void get500(){
		ATMdto atm = ATMtemplate.getForObject("http://localhost:8080/api/new-transaction?sum=500", ATMdto.class);
		Map<String, Integer> expected = new TreeMap<>();
		expected.put("ONEHUNDRED_RON(100)", 5);
		Assert.assertEquals(expected, atm.getBills());
	}
	@Test
	public void withdraw101Test() {
		ATMdto output = ATMtemplate.getForObject("http://localhost:8080/api/new-transaction?sum=101", ATMdto.class);
		Map<String, Integer> expected = new TreeMap<>();
		expected.put("ONEHUNDRED_RON(100)", 1);
		expected.put("ONE_RON(1)", 1);
		Assert.assertEquals(output.getBills(), expected);
		Assert.assertEquals("Transaction approved", output.getMessage());
	}
	
	@Test
	public void withdraw101and1000Test() {
		ATMdto output = ATMtemplate.getForObject("http://localhost:8080/api/new-transaction?sum=101", ATMdto.class);
		Map<String, Integer> expected = new TreeMap<>();
		expected.put("ONEHUNDRED_RON(100)", 1);
		expected.put("ONE_RON(1)", 1);
		Assert.assertEquals(expected, output.getBills());
		Assert.assertEquals("Transaction approved", output.getMessage());
		output = get("http://localhost:8080/api/new-transaction?sum=1000").as(ATMdto.class);
		expected.clear();
		expected.put("ONEHUNDRED_RON(100)", 10);
		Assert.assertEquals(expected, output.getBills());
		Assert.assertEquals("Transaction approved", output.getMessage());
	}
}