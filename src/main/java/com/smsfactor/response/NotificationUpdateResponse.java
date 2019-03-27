package com.smsfactor.response;

public class NotificationUpdateResponse extends NotificationGetReponse {
    public class Notification {

        public String alert_email;
        public String alert_gsm;
        public String email;
        public String phone_number;
        public String alert_trigger;
    }

    public Notification notification;
}