package com.smsfactor.net;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smsfactor.SMSFactor;
import com.smsfactor.exception.ApiConnectionException;
import com.smsfactor.exception.ApiException;
import com.smsfactor.exception.AuthenticationException;
import com.smsfactor.exception.InsufficientCreditsException;
import com.smsfactor.exception.InvalidRequestException;
import com.smsfactor.exception.SMSFactorException;
import com.smsfactor.exception.UnknownException;
import com.smsfactor.net.ApiResource.RequestMethod;
import com.smsfactor.response.SMSFactorResponse;

public class ApiRequestor
{
	private String apiToken;
	private String apiBase;
	private Client httpClient;
	
	public ApiRequestor()
	{
		this(null, null);
	}
	
	public ApiRequestor(String apiToken, String apiBase)
	{
		if(apiToken == null || apiToken.trim().isEmpty()) {
			apiToken = SMSFactor.apiToken;
		}
		this.apiToken = apiToken;
		if(apiBase == null || apiBase.trim().isEmpty()) {
			apiBase = SMSFactor.getApiBase();
		}
		this.apiBase = apiBase;
	}
	
	public <T> T request(ApiResource.RequestMethod method, String url, Class<T> clazz, ApiParameters smsFactorParams) throws SMSFactorException
	{
		ApiResponse response = this.rawRequest(method, url, smsFactorParams);
		T smsFactorResponse = this.interpretResponse(response, clazz);
		
		return smsFactorResponse;
	}
	
	private Map<String, Object> defaultHeaders()
	{
		Map<String, Object> defaultHeaders = new HashMap<String, Object>();
		defaultHeaders.put("Accept", "application/json");
		defaultHeaders.put("X-application", 16);
		defaultHeaders.put("Authorization", "Bearer " + this.apiToken);
		
		return defaultHeaders;
	}
	
	private ApiResponse rawRequest(ApiResource.RequestMethod method, String url, ApiParameters smsFactorParams) throws AuthenticationException, ApiConnectionException
	{
		String apiToken = SMSFactor.apiToken;
	    if(apiToken == null || apiToken.trim().isEmpty()) {
	      throw new AuthenticationException(
	          "No API token provided.  (HINT: set your API key using 'SMSFactor.apiToken = <API-TOKEN>'.");
	    }
	    
	    Map<String, Object> defaultHeaders = this.defaultHeaders();
	    
	    ApiResponse response = this.httpClient().request(method, this.apiBase, url, defaultHeaders, smsFactorParams);
	    
	    return response;
	}
	
	private <T> T interpretResponse(ApiResponse apiResponse, Class<T> clazz) throws SMSFactorException
	{
		String jsonFromResponse = apiResponse.getBody();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		
		T smsFactorResponse = null;
		smsFactorResponse = gson.fromJson(jsonFromResponse, clazz);
		((SMSFactorResponse) smsFactorResponse).setHttpResponseInformations(apiResponse.statusCode, apiResponse.body, apiResponse.headers);
		
		this.handleErrorResponse((SMSFactorResponse) smsFactorResponse);
		
		return smsFactorResponse;
	}
	
	private void handleErrorResponse(SMSFactorResponse response) throws SMSFactorException
	{
		SMSFactorException e = null;
		
		// Error returned by API (with code)
        if(response.status != null && response.status < 0) {
        	
        	switch(response.status) {
        		// Auth error
	        	case -1 : e = new AuthenticationException(response.message);		break;

	        	// XML error
	        	case -2 :
        		// JSON error
	        	case -6 :
        		// Data error
	        	case -7 : e = new InvalidRequestException(response.message);		break;
	        	
	        	// Insufficient credits
	        	case -3 : e = new InsufficientCreditsException(response.message);	break;

        		// Data error
	        	case -99 : e = new UnknownException(response.message);				break;
	        	
	        	default : e = new ApiException(response.message);					break;
        	}
        	
        	e.setSMSFactorCode(response.status);
        }
        // Error returned by API (no code)
        else if(response.error != null) {
        	e = new ApiException(response.error);
        }

        // There is an error
        if(e != null) {
        	e.setHttpResponseInformations(response.getStatusCode(), response.getBody(), response.getHeaders());
        	throw e;
        }
	}
	
	private Client httpClient()
    {
        if (this.httpClient == null) {
        	this.httpClient = Client.instance();
        }
        return this.httpClient;
    }
}
