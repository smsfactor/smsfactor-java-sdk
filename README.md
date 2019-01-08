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

## Documentation

Please see https://dev.smsfactor.com/fr/api/sms/bien-commencer for up-to-date documentation.