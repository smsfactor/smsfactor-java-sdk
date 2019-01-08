package com.smsfactor.response;

import java.util.List;

public class ContactListGetResponse extends SMSFactorResponse
{
	public class Contact {
		public String id;
		public String destination;
		public String info1;
		public String info2;
		public String info3;
		public String info4;
	}
	
	public List<Contact> list;
	public String name;
	public Integer contacts;
	public Integer totalRecords;
	public Integer totalDisplayRecords;
}
