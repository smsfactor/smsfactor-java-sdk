# SMSFactor SDK Java

You can sign up for a SMSFactor account at https://www.smsfactor.com.

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
    AccountCreditsResponse response = Account.credits();
    
#### Get account
    AccountGetResponse response = Account.get();

#### Get subaccounts
    AccountSubAccountsResponse response = Account.subAccounts();
    
#### Create an account
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
      
### Campaign
#### Send a campaign
    // Params
    Calendar delay = Calendar.getInstance(); // creates calendar
    delay.setTime(new Date()); // sets calendar time/date
    delay.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
    delay.getTime();
    
    String json = "{\"sms\": 
                        {
                            \"message\": {
                                 \"text\": \"tests sdk java\",
                                 \"pushtype\": \"alert\",               //alert(default) or marketing
                                 \"sender\": \"SDK JAVA\",              //Optional
                                 \"delay\": "+ delay +"                 //Optional. Omit for immediate send
                            },
                            \"recipients\": {
                                 \"gsm\": [{ 
                                        \"gsmsmsid\": \"100\", \"value\": \"33601000000\"
                                  }]
                            }   
                        }
                    }";

    ObjectMapper mapper = new ObjectMapper();
    Map<String,Object> map = mapper.readValue(json, Map.class);
            
    CampaignSendResponse response = Campaign.send(map, false);          // True to simulate the campaign (no SMS sent)

#### Send a campaign to a list
    // Params
    Calendar delay = Calendar.getInstance(); // creates calendar
    delay.setTime(new Date()); // sets calendar time/date
    delay.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
    delay.getTime();
    
    String json = "{\"sms\":
                        {
                            \"message\":{
                                \"text\":\"test skd java\",
                                \"pushtype\":\"alert\",                 //alert(default) or marketing
                                \"sender\":\"SDK\",                     //Optional
                                \"delay\": "+ delay +"                  //Optional. Omit for immediate send
                            },
                            \"lists\":[
                                {
                                    \"value\":\"your_list_id\",
                                }
                            ]
                        }
                    }";
                   
    ObjectMapper mapper = new ObjectMapper();
    Map<String,Object> param = mapper.readValue(json, Map.class);
            
    CampaignSendResponse response = Campaign.sendToLists(param, false); // True to simulate the campaign (no SMS sent)

#### Get information about a campaign
Use the campaign ticket value returned by our API after sending a campaign to get information about that campaign. 
Given the last example :

    CampaignGetResponse response = Campaign.get(ticket);

#### Cancel a delayed campaign
    CampaignHistoryResponse response = Campaign.history(ticket);
    
#### Get campaign history

     Map<String, Object> length = new HashMap<String, Object>();
     length.put("length", 5);
            
    CampaignHistoryResponse response = Campaign.history(length);        // Get the last 5 campaigns
    
### List
#### Create a list
    String json = "{\"list\": {
                            \"name\":\"Your list name\",
                            \"contacts\": {
                                    \"gsm\":[ 
                                                {
                                                    \"value\":\"33600000001\",
                                                    \"info1\":\"Lastname\",
                                                    \"info2\":\"Firstname\",
                                                    \"info3\":\"Gender\"
                                                },
                                                {   
                                                    \"value\":\"33600000002\",
                                                    \"info1\":\"Lastname\",
                                                    \"info2\":\"Firstname\",
                                                    \"info3\":\"Gender\"
                                                }
                                            ]
                                        }
                               }
                    }";
      ObjectMapper mapper = new ObjectMapper();
      Map<String,Object> param = mapper.readValue(json, Map.class);
      
      ContactListCreateResponse response = ContactList.create(param);
      Integer list_id = response.id;

#### Add contacts to a list
    String json = "{\"list\": {
                                \"list_id\": "+ list_id +",
                                \"contacts\": {
                                        \"gsm\":[ 
                                                    {
                                                        \"value\":\"33600000001\",
                                                        \"info1\":\"Lastname\",
                                                        \"info2\":\"Firstname\",
                                                        \"info3\":\"Gender\"
                                                    },
                                                    {   
                                                        \"value\":\"33600000002\",
                                                        \"info1\":\"Lastname\",
                                                        \"info2\":\"Firstname\",
                                                        \"info3\":\"Gender\"
                                                    }
                                                ]
                                              }
                               }
                    }";
      ObjectMapper mapper = new ObjectMapper();
      Map<String,Object> param = mapper.readValue(json, Map.class);
      
      ContactListAddContactsResponse response = ContactList.addContacts(param);
      
#### Get a list
    ContactListRemoveContactResponse response = ContactList.removeContact(contact_id); // Use Get list to get contact id
    
#### Remove a contact from a list
    ContactListGetResponse response = ContactList.get(list_id);
    
#### Deduplicate a list
    ContactListDeduplicateResponse response = ContactList.deduplicate(list_id);

#### Get all lists
    ContactListAllResponse response = ContactList.all();
    
#### Remove a list
    ContactListDeleteResponse response = ContactList.delete(list_id);
    
#### Add contacts to the blacklist
     String json = "{\"blacklist\": {
                                      \"contacts\": {
                                                \"gsm\":[
                                                            {
                                                                \"value\":\"33600000003\"
                                                            },
                                                            {   \"value\":\"33600000004\"
                                                            }
                                                        ]
                                                     }
                                     }
                     }";
    ObjectMapper mapper = new ObjectMapper();
    Map<String,Object> param = mapper.readValue(json, Map.class);
    ContactListAddToBlacklistResponse response = ContactList.addToBlacklist(param);

#### Get blacklist
    ContactListBlacklistResponse response = ContactList.blacklist();
    
#### Add contacts to the NPAI list
     String json = "{\"npai\": {
                                  \"contacts\": {
                                            \"gsm\":[
                                                        {
                                                            \"value\":\"33600000003\"
                                                        },
                                                        {   \"value\":\"33600000004\"
                                                        }
                                                    ]
                                                 }
                                 }
                         }";
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> param = mapper.readValue(json, Map.class);
        ContactListAddToNpaiResponse response = ContactList.addToNpai(param);
        
#### Get NPAI list
    ContactListNpaiResponse response = ContactList.npai();
    
### Token
#### Create a token
    String json = String json = "{\"token\":{
                                                \"name\":\"token sdk\"
                                            }
                                 }";
    ObjectMapper mapper = new ObjectMapper();
    Map<String,Object> param = mapper.readValue(json, Map.class);
    TokenCreateResponse response = Token.create(param);
    Integer token_id = response.token_id;
    
#### Get your tokens
    TokenAllResponse response = Token.all();
    
#### Delete a token
    TokenDeleteResponse response = Token.delete(token_id);
    
### Webhook
To see all available webhooks, please go to our official documentation.

#### Create a webhook
    String json = "{\"webhook\":{
                                    \"type\":\"DLR\",
                                    \"url\":\"https://yourserverurl.com\"
                                }
                    }";
            
    ObjectMapper mapper = new ObjectMapper();
    Map<String,Object> param = mapper.readValue(json, Map.class);
    WebhookCreateResponse response = Webhook.create(param);
    Integer webhook_id = response.webhook.webhook_id
    
#### Get your webhooks
    WebhookAllResponse response = Webhook.all();
    
#### Update a webhook
    String json = "{\"webhook\":{
                                    \"type\":\"MO\",
                                    \"url\":\"https://yourserverurl.com\"
                                }
                    }";
          
    ObjectMapper mapper = new ObjectMapper();
    Map<String,Object> param = mapper.readValue(json, Map.class);
    WebhookUpdateResponse response = Webhook.update(webhook_id, param);
    
#### Delete a webhook
    WebhookDeleteResponse response = Webhook.delete(webhook_id);
```
## Documentation

Please see https://dev.smsfactor.com/fr/api/sms/bien-commencer for up-to-date documentation.