package com.smsfactor.model;

import java.util.Map;
import com.smsfactor.exception.SMSFactorException;
import com.smsfactor.net.ApiParameters;
import com.smsfactor.net.ApiResource;
import com.smsfactor.response.TokenAllResponse;
import com.smsfactor.response.TokenCreateResponse;
import com.smsfactor.response.TokenDeleteResponse;

public abstract class Token extends ApiResource
{
	/**
	 * Get all your tokens.
	 * 
	 * @return
	 * @throws SMSFactorException
	 */
	public static TokenAllResponse all() throws SMSFactorException
	{
		String url = "/token";
		TokenAllResponse response = staticRequest(RequestMethod.GET, url, TokenAllResponse.class);
		
		return response;
	}
	
	
	/**
	 * Create a token.
	 * 
	 * @param params
	 * @return
	 * @throws SMSFactorException
	 */
	public static TokenCreateResponse create(Map<String, Object> params) throws SMSFactorException
	{
		String url = "/token";
		ApiParameters smsFactorParams = ApiParameters.initWithParameters(params);
		TokenCreateResponse response = staticRequest(RequestMethod.POST, url, TokenCreateResponse.class, smsFactorParams);
		
		return response;
	}
	
	
	/**
	 * Delete one of your tokens.
	 * 
	 * @param id
	 * @return
	 * @throws SMSFactorException
	 */
	public static TokenDeleteResponse delete(Integer id) throws SMSFactorException
	{
		String url = "/token/"+id;
		TokenDeleteResponse response = staticRequest(RequestMethod.DELETE, url, TokenDeleteResponse.class);
		
		return response;
	}
}
