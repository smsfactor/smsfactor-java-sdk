package com.smsfactor.net;

import com.smsfactor.exception.SMSFactorException;

public abstract class ApiResource
{
	public enum RequestMethod {
		GET, POST, DELETE, PUT
	}
	
	public static <T> T staticRequest(RequestMethod method, String url, Class<T> clazz) throws SMSFactorException
	{
		return staticRequest(method, url, clazz, new ApiParameters());
	}
	
	public static <T> T staticRequest(RequestMethod method, String url, Class<T> clazz, ApiParameters smsFactorParams) throws SMSFactorException
	{
		ApiRequestor requestor = new ApiRequestor();
		T smsFactorResponse = requestor.request(method, url, clazz, smsFactorParams);
		
		return smsFactorResponse;
	}

}
