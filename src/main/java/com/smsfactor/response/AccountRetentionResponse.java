package com.smsfactor.response;

public class AccountRetentionResponse extends SMSFactorResponse {
    public class Data_retention {
        
        public String message;
        public String list;     
        public String survey;
        public String campaign;
    }

    public Data_retention data_retention;
}
