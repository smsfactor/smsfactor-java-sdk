package com.smsfactor.exception;

public class AuthenticationException extends SMSFactorException
{
	private static final long serialVersionUID = 2L;
	
	public AuthenticationException(String message)
	{
		this(message, null, null);
	}
	
	public AuthenticationException(String message, Integer httpStatus, String httpBody)
	{
		super(message, httpStatus, httpBody);
	}
}
