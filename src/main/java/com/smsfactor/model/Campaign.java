package com.smsfactor.model;

import java.util.List;
import java.util.Map;
import com.smsfactor.exception.SMSFactorException;
import com.smsfactor.net.ApiParameters;
import com.smsfactor.response.CampaignHistoryResponse;
import com.smsfactor.response.CampaignNpaiToBlacklistResponse;
import com.smsfactor.response.CampaignSendResponse;
import com.smsfactor.response.CampaignCancelResponse;
import com.smsfactor.response.CampaignGetResponse;

public abstract class Campaign extends SMS {
	public String id;
	public String sender;
	public String text;
	public List<ContactList> lists;

	/**
	 * Send, or simulate, a campaign.
	 * 
	 * @param params the body params, see offical documentation https://dev.smsfactor.com
	 * @return the summary of the campaign
	 * @throws SMSFactorException
	 */
	public static CampaignSendResponse send(Map<String, Object> params) throws SMSFactorException {
		return send(params, false);
	}

	/**
	 * Send, or simulate, a campaign.
	 * 
	 * @param params the body params, see offical documentation https://dev.smsfactor.com
	 * @param simulate set to true to simulate to campaign (=no sending)
	 * @return the summary of the campaign
	 * @throws SMSFactorException
	 */
	public static CampaignSendResponse send(Map<String, Object> params, boolean simulate) throws SMSFactorException {
		String url = simulate ? "/send/simulate" : "/send";
		ApiParameters smsFactorParams = ApiParameters.initWithParameters(params);
		CampaignSendResponse response = staticRequest(RequestMethod.POST, url, CampaignSendResponse.class,
				smsFactorParams);

		return response;
	}

	/**
	 * Send, or simulate, a campaign to lists.
	 * 
	 * @param params the body params, see offical documentation https://dev.smsfactor.com
	 * @return the summary of the campaign
	 * @throws SMSFactorException
	 */
	public static CampaignSendResponse sendToLists(Map<String, Object> params) throws SMSFactorException {
		return sendToLists(params, false);
	}

	/**
	 * Send, or simulate, a campaign to lists.
	 * 
	 * @param params the body params, see offical documentation https://dev.smsfactor.com
	 * @param simulate set to true to simulate to campaign (=no sending)
	 * @return the summary of the campaign
	 * @throws SMSFactorException
	 */
	public static CampaignSendResponse sendToLists(Map<String, Object> params, boolean simulate)
			throws SMSFactorException {
		String url = simulate ? "/send/lists/simulate" : "/send/lists";
		ApiParameters smsFactorParams = ApiParameters.initWithParameters(params);
		CampaignSendResponse response = staticRequest(RequestMethod.POST, url, CampaignSendResponse.class,
				smsFactorParams);

		return response;
	}

	/**
	 * Get campaigns history.
	 * 
	 * @return the campaigns history
	 * @throws SMSFactorException
	 */
	public static CampaignHistoryResponse history() throws SMSFactorException {
		return history(null);
	}

	/**
	 * Get campaigns history.
	 * 
	 * @param params the body params, see offical documentation https://dev.smsfactor.com
	 * @return the campaigns history
	 * @throws SMSFactorException
	 */
	public static CampaignHistoryResponse history(Map<String, Object> params) throws SMSFactorException {
		String url = "/campaigns";
		ApiParameters smsFactorParams = ApiParameters.initWithQueryStringParameters(params);
		CampaignHistoryResponse response = staticRequest(RequestMethod.GET, url, CampaignHistoryResponse.class,
				smsFactorParams);

		return response;
	}

	/**
	 * Get one of your campaigns.
	 * 
	 * @param id the campaign id
	 * @return details about the campaign
	 * @throws SMSFactorException
	 */
	public static CampaignGetResponse get(Integer id) throws SMSFactorException {
		String url = "/campaign/" + id;
		CampaignGetResponse response = staticRequest(RequestMethod.GET, url, CampaignGetResponse.class);

		return response;
	}

	/**
	 * Cancel a future campaign.
	 * 
	 * @param id the campaign id
	 * @return success message
	 * @throws SMSFactorException
	 */
	public static CampaignCancelResponse cancel(Integer id) throws SMSFactorException {
		String url = "/send/" + id;
		CampaignCancelResponse response = staticRequest(RequestMethod.DELETE, url, CampaignCancelResponse.class);

		return response;
	}

	/**
	 * npai to blacklist.
	 * 
	 * @param id the campaign id
	 * @return the number of blacklisted numbers
	 * @throws SMSFactorException
	 */
	public static CampaignNpaiToBlacklistResponse npaiToBlacklist(Integer id) throws SMSFactorException {
		String url = "/campaign/"+id+"/npai";
		CampaignNpaiToBlacklistResponse response = staticRequest(RequestMethod.PUT, url,CampaignNpaiToBlacklistResponse.class);

		return response;
	}
}
