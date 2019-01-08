package com.smsfactor.response;

import java.util.Date;
import java.util.List;

public class TokenAllResponse extends SMSFactorResponse
{
	public class Token {
		public String name;
		public Integer api_token_id;
		public String api_token;
		public Date created_at;
	}
	
	public List<Token> tokens;
}
