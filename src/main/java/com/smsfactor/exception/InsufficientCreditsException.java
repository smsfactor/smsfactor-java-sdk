package com.smsfactor.exception;

public class InsufficientCreditsException extends SMSFactorException
{
	private static final long serialVersionUID = 2L;

	public InsufficientCreditsException(String message)
	{
		this(message, null, null);
	}
	
	public InsufficientCreditsException(String message, Integer httpStatus, String httpBody)
	{
		super(message, httpStatus, httpBody);
	}
}
