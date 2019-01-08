package com.smsfactor.response;

import java.util.Date;
import java.util.List;

public class CampaignGetResponse extends SMSFactorResponse
{
	public class Campaign {
		public Integer id;
		public String sender;
		public String text;
		public Date date;
		public Integer cost;
		public List<ContactList> lists;
		public Integer delivered;
		public Integer error;
		public Integer expired;
		public Integer network_error;
		public Integer stop;
		public Integer npai;
	}
	
	public class ContactList {
		public Integer id;
	}
	
	public List<Campaign> campaign;
}
