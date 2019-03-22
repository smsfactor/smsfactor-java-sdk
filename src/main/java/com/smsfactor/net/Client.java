package com.smsfactor.net;

import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.gson.GsonFactory;
import com.smsfactor.exception.ApiConnectionException;
import com.smsfactor.net.ApiResource.RequestMethod;

import org.apache.http.client.utils.URIBuilder;

public class Client implements ClientInterface
{
	private static Client instance;
	
	public static Client instance()
	{
		if(instance == null) {
			instance = new Client();
		}
		
		return instance;
	}
	
	public ApiResponse request(RequestMethod method, String baseUrl, String url, Map<String, Object> headers, ApiParameters smsFactorParams) throws ApiConnectionException
	{
		try {
			// Url construction
			String[] baseUrlParts = baseUrl.split("://");
			String scheme = (baseUrlParts.length == 1) ? "https" : baseUrlParts[0];
			String host = (baseUrlParts.length == 1) ? baseUrlParts[0] : baseUrlParts[1];
			URIBuilder uriBuilder = new URIBuilder()
					.setScheme(scheme)
					.setHost(host)
					.setPath(url);
			for(Map.Entry<String,Object> parameter : smsFactorParams.getQueryStringParams().entrySet()) {
				uriBuilder.setParameter(parameter.getKey(), parameter.getValue().toString());
			}
			URI uri = uriBuilder.build();
			
			HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
			HttpContent requestContent = (!smsFactorParams.getParams().isEmpty()) ? new JsonHttpContent(new GsonFactory(), smsFactorParams.getParams()) : null;
			HttpRequest request = requestFactory.buildRequest(method.toString(), new GenericUrl(uri), requestContent);
			
			// Headers
			HttpHeaders httpHeaders = request.getHeaders();
			for (Map.Entry<String,Object> header : headers.entrySet()) {
				if(header.getKey().equals("Authorization")) {
					httpHeaders.setAuthorization((String) header.getValue());
				}
				else if(header.getKey().equals("Accept")) {
					httpHeaders.setAccept((String) header.getValue());
				}
				else {
					httpHeaders.set(header.getKey(), header.getValue());
				}
			}
			
			HttpResponse response = request.execute();

			ApiResponse apiResponse = new ApiResponse(response.parseAsString(), response.getStatusCode(), response.getHeaders());
			return apiResponse;
		}
		catch(Exception e) {
			throw new ApiConnectionException(e.getMessage());
		}
	}

	public InputStream requestDownload(RequestMethod method, String baseUrl, String url, Map<String, Object> headers,ApiParameters smsFactorParams) throws ApiConnectionException
	 {
		try {
			// Url construction
			String[] baseUrlParts = baseUrl.split("://");
			String scheme = (baseUrlParts.length == 1) ? "https" : baseUrlParts[0];
			String host = (baseUrlParts.length == 1) ? baseUrlParts[0] : baseUrlParts[1];
			URIBuilder uriBuilder = new URIBuilder().setScheme(scheme).setHost(host).setPath(url);
			for (Map.Entry<String, Object> parameter : smsFactorParams.getQueryStringParams().entrySet()) {
				uriBuilder.setParameter(parameter.getKey(), parameter.getValue().toString());
			}
			URI uri = uriBuilder.build();

			HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
			HttpContent requestContent = (!smsFactorParams.getParams().isEmpty())
					? new JsonHttpContent(new GsonFactory(), smsFactorParams.getParams())
					: null;
			HttpRequest request = requestFactory.buildRequest(method.toString(), new GenericUrl(uri), requestContent);

			// Headers
			HttpHeaders httpHeaders = request.getHeaders();
			for (Map.Entry<String, Object> header : headers.entrySet()) {
				if (header.getKey().equals("Authorization")) {
					httpHeaders.setAuthorization((String) header.getValue());
				} else if (header.getKey().equals("Accept")) {
					httpHeaders.setAccept((String) header.getValue());
				} else {
					httpHeaders.set(header.getKey(), header.getValue());
				}
			}
			HttpResponse response = request.execute();

			return response.getContent();
		} catch (Exception e) {
			throw new ApiConnectionException(e.getMessage());
		}
	}
}
