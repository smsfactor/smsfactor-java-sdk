package com.smsfactor.response;

public class AccountUpdateRetentionResponse extends SMSFactorResponse {
    public class Retention {
        
        public String message;
        public String list;
        public String survey;
        public String campaign;
    }

    public Retention retention;
}
