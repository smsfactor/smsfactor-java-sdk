package com.smsfactor;

public abstract class SMSFactor
{
	// @var String The SMSFactor API token to be used for requests.
	public static String apiToken;
	
	// @var String The base URL for the SMSFactor API.
	private static String apiBase = "https://api.smsfactor.com";

	/**
     * Return the api base URL
     *
     * @return String
     */
	public static String getApiBase()
	{
		return apiBase;
	}
}
