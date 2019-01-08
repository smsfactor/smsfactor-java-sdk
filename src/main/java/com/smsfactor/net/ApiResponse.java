package com.smsfactor.net;

import java.util.Map;

public class ApiResponse
{
	protected String body;
	protected Integer statusCode;
	protected Map<String, Object> headers; 
	
	public ApiResponse(String body, Integer statusCode, Map<String, Object> headers)
	{
		this.body = body;
		this.statusCode = statusCode;
		this.headers = headers;
	}
	
	public String getBody()
	{
		return this.body;
	}
	
	public Integer getStatusCode()
	{
		return this.statusCode;
	}
	
	public Map<String, Object> getHeaders()
	{
		return this.headers;
	}
}
