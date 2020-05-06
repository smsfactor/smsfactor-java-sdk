package com.smsfactor.model;

import java.util.Map;

import com.smsfactor.exception.SMSFactorException;
import com.smsfactor.net.ApiParameters;
import com.smsfactor.net.ApiResource;
import com.smsfactor.response.NotificationGetResponse;
import com.smsfactor.response.NotificationUpdateResponse;

public abstract class Notification extends ApiResource
{
    /**
     * Get all your notification information.
     *
     * @return the notification information
     * @throws SMSFactorException
     */
    public static NotificationGetResponse get() throws SMSFactorException {
        String url = "/notification/balance";
        NotificationGetResponse response = staticRequest(RequestMethod.GET, url, NotificationGetResponse.class);

        return response;
    }

    /**
     * Update your notification information.
     *
     * @param params the body params, see offical documentation https://dev.smsfactor.com
     * @return a success message
     * @throws SMSFactorException
     */
	public static NotificationUpdateResponse update(Map<String, Object> params) throws SMSFactorException {
		String url = "/notification/balance";
		ApiParameters smsFactorParams = ApiParameters.initWithParameters(params);
		NotificationUpdateResponse response = staticRequest(RequestMethod.PUT, url,NotificationUpdateResponse.class,smsFactorParams);

		return response;
    }
}
