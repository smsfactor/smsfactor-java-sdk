package com.smsfactor.response;

import java.util.Date;
import java.util.List;

public class CampaignHistoryResponse extends SMSFactorResponse
{
	public class Campaign {
		public Integer id;
		public String sender;
		public String text;
		public Date date;
		public Integer cost;
		public Integer delivery_rate;
	}
	
	public List<Campaign> campaigns;
	public Integer totalRecords;
	public Integer totalDisplayRecords;
}
