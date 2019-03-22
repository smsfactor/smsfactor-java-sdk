package com.smsfactor.response;

import java.util.List;

public class InvoiceGetReponse extends SMSFactorResponse {
    public class invoices 
    {
        public String id;
        public String name;
        public String creation;
        public String validation;
        public String price;
        public String vat;
        public String total;
        public String currency;
        public String status;
        public List<products> lists;
        public String payment_type;
    }
    public class products {
        public String description;
        public String unit_price;
        public String quantities;
        public String price;
    }    
    public List<products> lists;    
}