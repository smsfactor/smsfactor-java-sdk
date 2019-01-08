package com.smsfactor.exception;

public class ApiConnectionException extends SMSFactorException
{
	private static final long serialVersionUID = 2L;

	public ApiConnectionException(String message)
	{
		this(message, null, null);
	}
	
	public ApiConnectionException(String message, Integer httpStatus, String httpBody)
	{
		super(message, httpStatus, httpBody);
	}
}
