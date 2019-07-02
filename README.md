# SMSFactor SDK Java

You can sign up for a SMSFactor account at https://www.smsfactor.com.

## Documentation

Please see https://dev.smsfactor.com/fr/api/sms/bien-commencer for up-to-date documentation.

## Installation

You'll need to manually install the following JARs:
* The SMSFactor JAR from https://xxx.xxxxxx.xx

## Usage

SMSFactorExample.java

```java
import java.util.HashMap;
import java.util.Map;

import com.smsfactor.SMSFactor;
import com.smsfactor.model.Campaign;
import com.smsfactor.response.CampaignHistoryResponse;
import com.smsfactor.exception.SMSFactorException;

public class SMSFactorExample
{
    public static void main(String[] args) {
        try {
            SMSFactor.apiToken = "xxxxxxxxxxxxxxxxx";
        
            // Parameters
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("length", 10);
            
            CampaignHistoryResponse response = Campaign.history(params);
            for(CampaignHistoryResponse.Campaign campaign:response.campaigns) {			
                System.out.println(campaign.id+" "+campaign.text+" "+campaign.date);
            }
        }
        catch(SMSFactorException e) {
            System.out.println(e.getMessage());
            System.out.println("SMS Factor error code : " + e.getSMSFactorCode());
        }
    }
}
```
## Example

### Account
#### Get credits
```java
AccountCreditsResponse response = Account.credits();
```
#### Get account
```java
AccountGetResponse response = Account.get();
```
#### Get subaccounts
```java
AccountSubAccountsResponse response = Account.subAccounts();
```
#### Create an account
```java
// Params
Map<String, String> user = new HashMap<String, String>();
user.put("firstname", "George");
user.put("lastname", "Abitbol");
user.put("city", "Pluvigner");
user.put("phone", "0011223344");
user.put("address1", "3 avenue du président Coty");
user.put("zip", "56330");
user.put("country_code", "fr");
user.put("isChild", "0"); //Is the account a subaccount ?
user.put("unlimited", "0"); //If isChild, should the subaccount use the parent's credits
user.put("email", "g.abitbol+" + UUID.randomUUID().toString() + "@smsfactor.com");
user.put("password", "edR3meDeDn0m");

Map<String, Object> params = new HashMap<String, Object>();
params.put("account", user);

AccountCreateResponse response = Account.create(params);
``` 
### Campaign
#### Send a campaign
```java
// Params
Map<String, String> message = new HashMap<String, String>();
message.put("text", "test skd java");
message.put("pushtype", "alert");                //alert(default) or marketing
message.put("sender", "SDK");                    //Optional
message.put("delay", "2019-06-08 10:21:15");     //Optional. Omit for immediate send

List<Map<String, String>> gsm = new ArrayList<Map<String, String>>();
gsm.add(
    new HashMap<String, String>() {{
        put("value", "33601000000");
    }}
);

Map<String, List<Map<String, String>>> recipients = new HashMap<String, List<Map<String, String>>>();
recipients.put("gsm", gsm);

Map<String, Object> sms = new HashMap<String, Object>();
sms.put("message", message);
sms.put("recipients", recipients);

Map<String, Object> payload = new HashMap<String, Object>();
payload.put("sms", sms);
        
CampaignSendResponse response = Campaign.send(payload, false);  // True to simulate the campaign (no SMS sent)
```
#### Send a campaign to a list
```java
// Params
Map<String, String> message = new HashMap<String, String>();
message.put("text", "test skd java");
message.put("pushtype", "alert");               //alert(default) or marketing
message.put("sender", "SDK");                   //Optional
message.put("delay", "2019-06-08 10:21:15");    //Optional. Omit for immediate send

List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
lists.add(
    new HashMap<String, String>() {{
        put("value", "your_list_id");
    }}
);

Map<String, Object> sms = new HashMap<String, Object>();
sms.put("message", message);
sms.put("lists", lists);

Map<String, Object> payload = new HashMap<String, Object>();
payload.put("sms", sms);
        
CampaignSendResponse response = Campaign.sendToLists(payload, false); // True to simulate the campaign (no SMS sent)
```
#### Get information about a campaign
Use the campaign ticket value returned by our API after sending a campaign to get information about that campaign. 
Given the last example :
```java
CampaignGetResponse response = Campaign.get(ticket);
```
#### Cancel a delayed campaign
```java
CampaignHistoryResponse response = Campaign.history(ticket);
```    
#### Get campaign history
```java
Map<String, Object> length = new HashMap<String, Object>();
length.put("length", 5);
        
CampaignHistoryResponse response = Campaign.history(length);        // Get the last 5 campaigns
```
### List
#### Create a list
You can customize each contact with up to 4 optional information
```java
List<Map<String, String>> gsm = new ArrayList<Map<String, String>>();
gsm.add(
    new HashMap<String, String>() {{
        put("value", "33600000001");
        put("info1", "Lastname");
        put("info2", "Firstname");
        put("info3", "Gender");
    }}
);
gsm.add(
    new HashMap<String, String>() {{
        put("value", "33600000002");
        put("info1", "Lastname");
        put("info2", "Firstname");
        put("info3", "Gender");
    }}
);

Map<String, Object> contacts = new HashMap<String, Object>();
contacts.put("gsm", gsm);

Map<String, Object> object = new HashMap<String, Object>();
object.put("name", "sdk list");
object.put("contacts", contacts);

Map<String, Object> payload = new HashMap<String, Object>();
payload.put("list", object);
  
ContactListCreateResponse response = ContactList.create(payload);
Integer list_id = response.id;
```
#### Add contacts to a list
```java
List<Map<String, String>> gsm = new ArrayList<Map<String, String>>();
gsm.add(
    new HashMap<String, String>() {{
        put("value", "33600000003");
        put("info1", "Lastname");
        put("info2", "Firstname");
        put("info3", "Gender");
    }}
);
gsm.add(
    new HashMap<String, String>() {{
        put("value", "33600000004");
        put("info1", "Lastname");
        put("info2", "Firstname");
        put("info3", "Gender");
    }}
);

Map<String, Object> contacts = new HashMap<String, Object>();
contacts.put("gsm", gsm);

Map<String, Object> object = new HashMap<String, Object>();
object.put("list", list_id);
object.put("contacts", contacts);

Map<String, Object> payload = new HashMap<String, Object>();
payload.put("list", object);

ContactListAddContactsResponse response = ContactList.addContacts(payload);
```     
#### Get a list
```java
ContactListRemoveContactResponse response = ContactList.removeContact(contact_id); // Use Get list to get contact id
```  
#### Remove a contact from a list
```java
ContactListGetResponse response = ContactList.get(list_id);
```   
#### Deduplicate a list
```java
ContactListDeduplicateResponse response = ContactList.deduplicate(list_id);
```
#### Get all lists
```java
ContactListAllResponse response = ContactList.all();
```  
#### Remove a list
```java
ContactListDeleteResponse response = ContactList.delete(list_id);
```    
#### Add contacts to the blacklist
```java
List<Map<String, String>> gsm = new ArrayList<Map<String, String>>();
gsm.add(
    new HashMap<String, String>() {{
        put("value", "33600000003");
    }}
);
gsm.add(
    new HashMap<String, String>() {{
        put("value", "33600000004");
    }}
);

Map<String, Object> contacts = new HashMap<String, Object>();
contacts.put("gsm", gsm);

Map<String, Object> object = new HashMap<String, Object>();
object.put("contacts", contacts);

Map<String, Object> payload = new HashMap<String, Object>();
payload.put("blacklist", object);
ContactListAddToBlacklistResponse response = ContactList.addToBlacklist(payload);
```
#### Get blacklist
```java
    ContactListBlacklistResponse response = ContactList.blacklist();
```    
#### Add contacts to the NPAI list
```java
List<Map<String, String>> gsm = new ArrayList<Map<String, String>>();
gsm.add(
    new HashMap<String, String>() {{
        put("value", "33600000003");
    }}
);
gsm.add(
    new HashMap<String, String>() {{
        put("value", "33600000004");
    }}
);

Map<String, Object> contacts = new HashMap<String, Object>();
contacts.put("gsm", gsm);

Map<String, Object> object = new HashMap<String, Object>();
object.put("contacts", contacts);

Map<String, Object> payload = new HashMap<String, Object>();
payload.put("npai", object);
ContactListAddToNpaiResponse response = ContactList.addToNpai(payload);
```        
#### Get NPAI list
```java
ContactListNpaiResponse response = ContactList.npai();
```  
### Token
#### Create a token
```java
Map<String, String> token = new HashMap<String, String>();
token.put("name", "token sdk");

Map<String, Object> payload = new HashMap<String, Object>();
payload.put("token", token);
TokenCreateResponse response = Token.create(payload);
Integer token_id = response.token_id;
```
#### Get your tokens
```java
TokenAllResponse response = Token.all();
```    
#### Delete a token
```java
TokenDeleteResponse response = Token.delete(token_id);
```  
### Webhook
To see all available webhooks, please go to our official documentation.

#### Create a webhook
```java
Map<String, String> webhook = new HashMap<String, String>();
webhook.put("type", "DLR");
webhook.put("url", "https://yourserverurl.com");

Map<String, Object> payload = new HashMap<String, Object>();
payload.put("webhook", webhook);
WebhookCreateResponse response = Webhook.create(payload);
Integer webhook_id = response.webhook.webhook_id
``` 
#### Get your webhooks
```java
WebhookAllResponse response = Webhook.all();
``` 
#### Update a webhook
```java
Map<String, String> webhook = new HashMap<String, String>();
webhook.put("type", "MO");
webhook.put("url", "https://yourserverurl.net");

Map<String, Object> payload = new HashMap<String, Object>();
payload.put("webhook", webhook);
WebhookUpdateResponse response = Webhook.update(webhook_id, payload);
```  
#### Delete a webhook
```java
WebhookDeleteResponse response = Webhook.delete(webhook_id);
 ```