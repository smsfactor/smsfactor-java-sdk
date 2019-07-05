package com.smsfactor.model;

import java.util.Map;
import com.smsfactor.exception.SMSFactorException;
import com.smsfactor.net.ApiParameters;
import com.smsfactor.net.ApiResource;
import com.smsfactor.response.WebhookAllResponse;
import com.smsfactor.response.WebhookCreateResponse;
import com.smsfactor.response.WebhookDeleteResponse;
import com.smsfactor.response.WebhookUpdateResponse;

public abstract class Webhook extends ApiResource
{
	public static final String TYPE_MO = "MO";
	public static final String TYPE_DLR = "DLR";
	public static final String TYPE_STOP = "STOP";
	public static final String TYPE_CLICKER = "CLICKER";
	
	
	/**
	 * Create a webhook.
	 * 
	 * @param params the body params, see offical documentation https://dev.smsfactor.com
	 * @return a success message
	 * @throws SMSFactorException
	 */
	public static WebhookCreateResponse create(Map<String, Object> params) throws SMSFactorException
	{
		String url = "/webhook";
		ApiParameters smsFactorParams = ApiParameters.initWithParameters(params);
		WebhookCreateResponse response = staticRequest(RequestMethod.POST, url, WebhookCreateResponse.class, smsFactorParams);
		
		return response;
	}
	
	
	/**
	 * Update a webhook.
	 * 
	 * @param id the webhook id
	 * @param params the body params, see offical documentation https://dev.smsfactor.com
	 * @return a success message
	 * @throws SMSFactorException
	 */
	public static WebhookUpdateResponse update(Integer id, Map<String, Object> params) throws SMSFactorException
	{
		String url = "/webhook/"+id;
		ApiParameters smsFactorParams = ApiParameters.initWithParameters(params);
		WebhookUpdateResponse response = staticRequest(RequestMethod.PUT, url, WebhookUpdateResponse.class, smsFactorParams);
		
		return response;
	}
	
	
	/**
	 * Get all your webhooks.
	 * 
	 * @return a webhook list
	 * @throws SMSFactorException
	 */
	public static WebhookAllResponse all() throws SMSFactorException
	{
		String url = "/webhook";
		WebhookAllResponse response = staticRequest(RequestMethod.GET, url, WebhookAllResponse.class);
		
		return response;
	}
	
	
	/**
	 * Delete one of your webhooks.
	 * 
	 * @param id the webhook id
	 * @return a success message
	 * @throws SMSFactorException
	 */
	public static WebhookDeleteResponse delete(Integer id) throws SMSFactorException
	{
		String url = "/webhook/"+id;
		WebhookDeleteResponse response = staticRequest(RequestMethod.DELETE, url, WebhookDeleteResponse.class);
		
		return response;
	}
}
