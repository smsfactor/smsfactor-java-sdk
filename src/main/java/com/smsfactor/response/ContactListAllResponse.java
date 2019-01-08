package com.smsfactor.response;

import java.util.Date;
import java.util.List;

public class ContactListAllResponse extends SMSFactorResponse
{
	public class ContactList {
		public Integer id;
		public String name;
		public Date date;
		public Integer count;
	}
	
	public List<ContactList> lists;
}
