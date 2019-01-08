package com.smsfactor.net;

import java.util.HashMap;
import java.util.Map;

public class ApiParameters
{
	protected Map<String, Object> params;
	protected Map<String, Object> queryStringParams;
	
	public ApiParameters()
	{
		this(null);
	}

	public ApiParameters(Map<String, Object> params)
	{
		this(params, null);
	}

	public ApiParameters(Map<String, Object> params, Map<String, Object> queryStringParams)
	{
		this.params = (params == null) ? new HashMap<String, Object>() : params;
		this.queryStringParams = (queryStringParams == null) ? new HashMap<String, Object>() : queryStringParams;
	}
	
	public static ApiParameters initWithParameters(Map<String, Object> params)
	{
		return new ApiParameters(params);
	}
	
	public static ApiParameters initWithQueryStringParameters(Map<String, Object> queryStringParams)
	{
		return new ApiParameters(null, queryStringParams);
	}
	
	public Map<String, Object> getParams()
	{
		return this.params;
	}
	
	public Map<String, Object> getQueryStringParams()
	{
		return this.queryStringParams;
	}
}
