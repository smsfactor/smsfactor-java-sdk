package com.smsfactor.exception;

public class ApiException extends SMSFactorException
{
	private static final long serialVersionUID = 2L;

	public ApiException(String message)
	{
		this(message, null, null);
	}
	
	public ApiException(String message, Integer httpStatus, String httpBody)
	{
		super(message, httpStatus, httpBody);
	}
}
