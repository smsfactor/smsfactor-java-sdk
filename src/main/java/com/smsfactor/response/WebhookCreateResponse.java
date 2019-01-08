package com.smsfactor.response;

public class WebhookCreateResponse extends SMSFactorResponse
{
	public class Webhook {
		public Integer webhook_id;
		public String type;
		public String url;
	}
	
	public Webhook webhook;
}
