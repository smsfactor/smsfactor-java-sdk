package com.smsfactor.model;

import java.util.Map;
import com.smsfactor.exception.SMSFactorException;
import com.smsfactor.net.ApiParameters;
import com.smsfactor.net.ApiResource;
import com.smsfactor.response.ContactListAddContactsResponse;
import com.smsfactor.response.ContactListAddToBlacklistResponse;
import com.smsfactor.response.ContactListAddToNpaiResponse;
import com.smsfactor.response.ContactListAllResponse;
import com.smsfactor.response.ContactListBlacklistResponse;
import com.smsfactor.response.ContactListCreateResponse;
import com.smsfactor.response.ContactListDeduplicateResponse;
import com.smsfactor.response.ContactListDeleteResponse;
import com.smsfactor.response.ContactListGetResponse;
import com.smsfactor.response.ContactListNpaiResponse;
import com.smsfactor.response.ContactListRemoveContactResponse;
import com.smsfactor.response.ContactListnpaiClearResponse;

public abstract class ContactList extends ApiResource
{
	/**
	 * Create a list.
	 * 
	 * @param params
	 * @return
	 * @throws SMSFactorException
	 */
	public static ContactListCreateResponse create(Map<String, Object> params) throws SMSFactorException
	{
		String url = "/list";
		ApiParameters smsFactorParams = ApiParameters.initWithParameters(params);
		ContactListCreateResponse response = staticRequest(RequestMethod.POST, url, ContactListCreateResponse.class, smsFactorParams);
		
		return response;
	}
	
	
	/**
	 * Get all your lists.
	 * 
	 * @return
	 * @throws SMSFactorException
	 */
	public static ContactListAllResponse all() throws SMSFactorException
	{
		String url = "/lists";
		ContactListAllResponse response = staticRequest(RequestMethod.GET, url, ContactListAllResponse.class);
		
		return response;
	}
	
	
	/**
	 * Get one of your lists.
	 * 
	 * @param id
	 * @return
	 * @throws SMSFactorException
	 */
	public static ContactListGetResponse get(Integer id) throws SMSFactorException
	{
		String url = "/list/"+id;
		ContactListGetResponse response = staticRequest(RequestMethod.GET, url, ContactListGetResponse.class);
		
		return response;
	}
	
	
	/**
	 * Delete one of your lists.
	 * 
	 * @param id
	 * @return
	 * @throws SMSFactorException
	 */
	public static ContactListDeleteResponse delete(Integer id) throws SMSFactorException
	{
		String url = "/list/"+id;
		ContactListDeleteResponse response = staticRequest(RequestMethod.DELETE, url, ContactListDeleteResponse.class);
		
		return response;
	}
	
	
	/**
	 * Add contacts to a list.
	 * 
	 * @param params
	 * @return
	 * @throws SMSFactorException
	 */
	public static ContactListAddContactsResponse addContacts(Map<String, Object> params) throws SMSFactorException
	{
		String url = "/list";
		ApiParameters smsFactorParams = ApiParameters.initWithParameters(params);
		ContactListAddContactsResponse response = staticRequest(RequestMethod.POST, url, ContactListAddContactsResponse.class, smsFactorParams);
		
		return response;
	}
	
	
	/**
	 * Remove a contact from a list.
	 * 
	 * @param id
	 * @return
	 * @throws SMSFactorException
	 */
	public static ContactListRemoveContactResponse removeContact(String id) throws SMSFactorException
	{
		String url = "/list/contact/"+id;
		ContactListRemoveContactResponse response = staticRequest(RequestMethod.DELETE, url, ContactListRemoveContactResponse.class);
		
		return response;
	}
	
	
	/**
	 * Deduplicate a list.
	 * 
	 * @param id
	 * @return
	 * @throws SMSFactorException
	 */
	public static ContactListDeduplicateResponse deduplicate(Integer id) throws SMSFactorException
	{
		String url = "/list/deduplicate/"+id;
		ContactListDeduplicateResponse response = staticRequest(RequestMethod.PUT, url, ContactListDeduplicateResponse.class);
		
		return response;
	}
	
	
	/**
	 * Get your blacklist.
	 * 
	 * @param id
	 * @return
	 * @throws SMSFactorException
	 */
	public static ContactListBlacklistResponse blacklist() throws SMSFactorException
	{
		String url = "/blacklist";
		ContactListBlacklistResponse response = staticRequest(RequestMethod.GET, url, ContactListBlacklistResponse.class);
		
		return response;
	}
	
	
	/**
	 * Add contacts to your blacklist.
	 * 
	 * @param params
	 * @return
	 * @throws SMSFactorException
	 */
	public static ContactListAddToBlacklistResponse addToBlacklist(Map<String, Object> params) throws SMSFactorException
	{
		String url = "/blacklist";
		ApiParameters smsFactorParams = ApiParameters.initWithParameters(params);
		ContactListAddToBlacklistResponse response = staticRequest(RequestMethod.POST, url, ContactListAddToBlacklistResponse.class, smsFactorParams);
		
		return response;
	}
	
	
	/**
	 * Get your NPAI list (unassigned numbers).
	 * 
	 * @return
	 * @throws SMSFactorException
	 */
	public static ContactListNpaiResponse npai() throws SMSFactorException
	{
		String url = "/npai";
		ContactListNpaiResponse response = staticRequest(RequestMethod.GET, url, ContactListNpaiResponse.class);
		
		return response;
	}
	
	
	/**
	 * Add contacts to the NPAI list (unassigned numbers).
	 */
	public static ContactListAddToNpaiResponse addToNpai(Map<String, Object> params) throws SMSFactorException
	{
		String url = "/npai";
		ApiParameters smsFactorParams = ApiParameters.initWithParameters(params);
		ContactListAddToNpaiResponse response = staticRequest(RequestMethod.POST, url, ContactListAddToNpaiResponse.class, smsFactorParams);
		
		return response;
	}

	/**
	 * Delete npai from list 
	 * @param id
	 * @return
	 */
	public static ContactListnpaiClearResponse npaiClear(Integer id) throws SMSFactorException {
		String url = "/list/"+id+"/npai/clear";
		ContactListnpaiClearResponse response = staticRequest(RequestMethod.PUT, url,ContactListnpaiClearResponse.class);

		return response;
	}

}
