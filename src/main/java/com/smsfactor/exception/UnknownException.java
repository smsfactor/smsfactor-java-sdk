package com.smsfactor.exception;

public class UnknownException extends SMSFactorException
{
	private static final long serialVersionUID = 2L;

	public UnknownException(String message)
	{
		this(message, null, null);
	}
	
	public UnknownException(String message, Integer httpStatus, String httpBody)
	{
		super(message, httpStatus, httpBody);
	}
}
