package com.smsfactor.model;

import java.util.Map;
import com.smsfactor.exception.SMSFactorException;
import com.smsfactor.net.ApiParameters;
import com.smsfactor.response.MessageSendResponse;

public abstract class Message extends SMS
{
	/**
	 * Send, or simulate, a message.
	 * 
	 * @param params
	 * @return
	 * @throws SMSFactorException
	 */
	public static MessageSendResponse send(Map<String, Object> params) throws SMSFactorException
	{
		return send(params, false);
	}
	public static MessageSendResponse send(Map<String, Object> params, boolean simulate) throws SMSFactorException
	{
		String url = simulate ? "/send/simulate" : "/send";
		ApiParameters smsFactorParams = ApiParameters.initWithQueryStringParameters(params);
		MessageSendResponse response = staticRequest(RequestMethod.GET, url, MessageSendResponse.class, smsFactorParams);
		
		return response;
	}
}
