package com.smsfactor.response;

public class AccountGetResponse extends SMSFactorResponse
{	
	public class Account {
		public Integer client_id;
		public String email;
		public String firstname;
		public String lastname;
		public String city;
		public String type;
		public String company;
		public String phone;
		public String address1;
		public String address2;
		public String zip;
		public String country;
		public String country_code;
		public String lang;
		public Integer credits;
		public Integer unlimited;
		public String description;
		public String senderid;
		public Integer status;
	}
	
	public Account account;
}
