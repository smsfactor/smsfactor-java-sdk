package com.smsfactor.exception;

import java.util.Map;

public class SMSFactorException extends Exception
{
	private static final long serialVersionUID = 2L;
	
	protected Integer httpStatusCode;
	protected String httpBody;
	protected Map<String, Object> httpHeaders;
	protected Integer smsFactorCode;
	
	public SMSFactorException(String message)
	{
		this(message, null, null);
	}
	
	public SMSFactorException(String message, Integer httpStatusCode, String httpBody)
	{
		super(message);
		
		this.httpStatusCode = httpStatusCode;
		this.httpBody = httpBody;
	}
	
	public void setHttpResponseInformations(Integer httpStatusCode, String httpBody, Map<String, Object> httpHeaders)
	{
		this.httpStatusCode = httpStatusCode;
		this.httpBody = httpBody;
		this.httpHeaders = httpHeaders;
	}
	
	public void setSMSFactorCode(Integer smsFactorCode)
	{
		this.smsFactorCode = smsFactorCode;
	}
	
	public Integer getHttpStatusCode()
	{
		return this.httpStatusCode;
	}
	
	public String getHttpBody()
	{
		return this.httpBody;
	}
	
	public Map<String, Object> getHttpHeaders()
	{
		return this.httpHeaders;
	}
	
	public Integer getSMSFactorCode()
	{
		return this.smsFactorCode;
	}
}
