package com.smsfactor.response;

import java.util.ArrayList;

public class WebhookAllResponse extends SMSFactorResponse
{
	public class Webhook {
		public Integer webhook_id;
		public String type;
		public String url;
	}
	
	public ArrayList<Webhook> webhooks;
}
