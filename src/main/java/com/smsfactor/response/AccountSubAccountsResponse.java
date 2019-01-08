package com.smsfactor.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AccountSubAccountsResponse extends SMSFactorResponse
{	
	public class Account {
		public Integer client_id;
		public String email;
		public String firstname;
		public String lastname;
		public String city;
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
		public Integer senderid;
		public Integer status;
	}
	
	@SerializedName("sub-accounts") 
	public List<Account> subAccounts;
}
