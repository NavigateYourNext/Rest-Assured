package com.testing;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest 
{
	@Test
	void getWeatherDetails()
	{
		//Specify base URI
		RestAssured.baseURI = "https://reqres.in/api/users";
		
		//Request Object Creator
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object
		Response response = httpRequest.request(Method.GET,"/2");
		
		//Print the response
		String responseBody = response.getBody().asString();
		System.out.println("Response Body Is: "+responseBody);
		
		//Status Code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		//Status Line
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		//Capture Header Details
		String contentType = response.header("Content-Type");
		System.out.println("Content Type Is: "+contentType);
		
		//Print All Headers
		Headers headers = response.headers();
		System.out.println("Headers Are: ");
		for(Header header : headers)
		{
			System.out.println(header.getName()+" "+header.getValue());
		}
		
		//Check Specific Data Is Present or Not
		Assert.assertEquals(responseBody.contains("janet.weaver@reqres.in"), true);
		
		//Get JSON Values
		JsonPath jsonPathh = response.jsonPath();
		System.out.println("ID is: "+jsonPathh.get("id"));
		System.out.println("Email Is: "+jsonPathh.get("email"));
		
		//Basic Authentication (write this code before given())
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("test");
		authScheme.setPassword("test");
		
		RestAssured.authentication = authScheme;
		
	}
}
