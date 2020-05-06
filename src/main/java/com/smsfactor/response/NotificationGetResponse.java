package com.smsfactor.response;

public class NotificationGetResponse extends SMSFactorResponse {
    public class Notification {

        public String alert_email;
        public String alert_gsm;
        public String email;
        public String phone_number;
        public String alert_trigger;
    }

    public Notification notification;
}
