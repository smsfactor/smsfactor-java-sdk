package com.smsfactor.response;

import java.util.Map;

public class SMSFactorResponse
{
	/**
	 * Http response
	 */
	protected Integer statusCode;
	protected String body;
	protected Map<String, Object> headers;
	
	/**
	 * SMSFactor response
	 */
	public Integer status;
	public String message;
	public String error;
	
	
	public void setHttpResponseInformations(Integer statusCode, String body, Map<String, Object> headers)
	{
		this.statusCode = statusCode;
		this.body = body;
		this.headers = headers;
	}
	
	public int getStatusCode()
	{
		return this.statusCode;
	}
	
	public String getBody()
	{
		return this.body;
	}
	
	public Map<String, Object> getHeaders()
	{
		return this.headers;
	}
}
