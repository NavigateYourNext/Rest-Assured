package com.testing;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {


	@Test
	void postRequest()
	{
		//Specify base URI
		RestAssured.baseURI = "https://reqres.in/api";

		//Request Object Creator
		RequestSpecification httpRequest = RestAssured.given();

		//Request PayLoad
		JSONObject requestParamas = new JSONObject();
		requestParamas.put("name", "morpheus");
		requestParamas.put("job", "tester");

		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParamas.toJSONString());

		//Response Object
		Response response = httpRequest.request(Method.POST,"/create");

		//Print the response
		String responseBody = response.getBody().asString();
		System.out.println("Response Body Is: "+responseBody);

		//Status Code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);

		//Status Line
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");

		/*//response.jsonPath().get("") will give us particular field value in JSON
		String id = response.jsonPath().get("job");
		System.out.println("ID is: "+id);*/
		
		
		

	}

}
