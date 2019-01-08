package com.smsfactor.response;

import java.util.ArrayList;

public class ContactListNpaiResponse extends SMSFactorResponse
{
	public class Contact {
		public String id;
		public String destination;
		public String info1;
		public String info2;
		public String info3;
		public String info4;
	}
	
	public ArrayList<Contact> list;
	public Integer totalRecords;
	public Integer totalDisplayRecords;
}
