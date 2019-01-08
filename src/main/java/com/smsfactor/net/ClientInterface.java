package com.smsfactor.net;

import java.util.Map;
import com.smsfactor.exception.ApiConnectionException;

public interface ClientInterface
{
	public ApiResponse request(ApiResource.RequestMethod method, String baseUrl, String url, Map<String, Object> headers, ApiParameters smsFactorParams) throws ApiConnectionException;
}
