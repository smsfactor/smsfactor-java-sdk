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
	 * @param params the body params, see offical documentation https://dev.smsfactor.com
	 * @return the campaign summary
	 * @throws SMSFactorException
	 */
	public static MessageSendResponse send(Map<String, Object> params) throws SMSFactorException
	{
		return send(params, false);
	}

	/**
	 * Send, or simulate, a message.
	 * 
	 * @param params the body params, see offical documentation https://dev.smsfactor.com
	 * @param simulate set to true to simulate to campaign (=no sending)
	 * @return the campaign summary
	 * @throws SMSFactorException
	 */
	public static MessageSendResponse send(Map<String, Object> params, boolean simulate) throws SMSFactorException
	{
		String url = simulate ? "/send/simulate" : "/send";
		ApiParameters smsFactorParams = ApiParameters.initWithQueryStringParameters(params);
		MessageSendResponse response = staticRequest(RequestMethod.GET, url, MessageSendResponse.class, smsFactorParams);
		
		return response;
	}
}
