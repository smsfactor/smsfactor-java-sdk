package com.smsfactor.exception;

public class InvalidRequestException extends SMSFactorException
{
	private static final long serialVersionUID = 2L;

	public InvalidRequestException(String message)
	{
		this(message, null, null);
	}
	
	public InvalidRequestException(String message, Integer httpStatus, String httpBody)
	{
		super(message, httpStatus, httpBody);
	}
}
