package com.example.ATMProject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class IntegrationTests {
	@Test
	public void withdraw101Test() throws JSONException {
		RestAssured.baseURI = "http://localhost:8080";
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("sum", "101");
		Response response = request.get("/api/new-transaction");
		ResponseBody body = response.getBody();
		ATMOutput ATMbody = body.as(ATMOutput.class);
		Assert.assertEquals("Transaction approved", ATMbody.message);
		
	}
}