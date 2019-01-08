package com.smsfactor.model;

import java.util.Map;
import com.smsfactor.exception.SMSFactorException;
import com.smsfactor.net.ApiParameters;
import com.smsfactor.net.ApiResource;
import com.smsfactor.response.AccountCreateResponse;
import com.smsfactor.response.AccountCreditsResponse;
import com.smsfactor.response.AccountGetResponse;
import com.smsfactor.response.AccountSubAccountsResponse;

public abstract class Account extends ApiResource
{
	public static final String TYPE_COMPANY = "company";
	public static final String TYPE_ASSOCIATION = "association";
	public static final String TYPE_ADMINISTRATION = "administration";
	public static final String TYPE_PRIVATE = "private";
	
	
	/**
	 * Create an account.
	 * 
	 * @param params
	 * @return
	 * @throws SMSFactorException
	 */
	public static AccountCreateResponse create(Map<String, Object> params) throws SMSFactorException
	{
		String url = "/account";
		ApiParameters smsFactorParams = ApiParameters.initWithParameters(params);
		AccountCreateResponse response = staticRequest(RequestMethod.POST, url, AccountCreateResponse.class, smsFactorParams);
		
		return response;
	}
	
	
	/**
	 * Retrieve your account informations.
	 * 
	 * @return
	 * @throws SMSFactorException
	 */
	public static AccountGetResponse get() throws SMSFactorException
	{
		String url = "/account";
		AccountGetResponse response = staticRequest(RequestMethod.GET, url, AccountGetResponse.class);
		
		return response;
	}
	
	
	/**
	 * Get all your sub-accounts.
	 * 
	 * @return
	 * @throws SMSFactorException
	 */
	public static AccountSubAccountsResponse subAccounts() throws SMSFactorException
	{
		String url = "/sub-accounts";
		AccountSubAccountsResponse response = staticRequest(RequestMethod.GET, url, AccountSubAccountsResponse.class);
		
		return response;
	}
	
	
	/**
	 * Get remaining credits on your account.
	 * 
	 * @return
	 * @throws SMSFactorException
	 */
	public static AccountCreditsResponse credits() throws SMSFactorException
	{
		String url = "/credits";
		AccountCreditsResponse response = staticRequest(RequestMethod.GET, url, AccountCreditsResponse.class);
		
		return response;
	}
}
