package com.smsfactor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smsfactor.exception.SMSFactorException;
import com.smsfactor.model.Invoice;

public class SMSFactorExample {
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        try {
            SMSFactor.apiToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxODU1OCIsImlhdCI6MTUzOTE2NzIxOX0.XyJvFdgrAhfmiwzzSYuxNcIP51NOHI-KHv0tIbFcmJg";
            // SMSFactor.apiToken =
            // "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTcyMSIsImlhdCI6MTU1MDg1MDE2NH0.1MH84-T4gNtaNBdLkZNVMKOwrCXWRNmkbGeRBTzVG4k";

            // ObjectMapper mapper = new ObjectMapper();
            // // Parameters
            // Map<String, Object> params = new HashMap<String, Object>();
            // params.put("text", "test");
            // params.put("to", "0675510141");

            // String jsonInput =
            // "{\"npai\":{\"contacts\":{\"gsm\":[{\"value\":\"33601020306\",\"info1\":\"Hiroo\",\"info2\":\"Onoda\"},{\"value\":\"33601020307\",\"info1\":\"Grace\",\"info2\":\"Hopper\"},{\"value\":\"33601020308\",\"info1\":\"Hedy\",\"info2\":\"Lamarr\",\"info3\":
            // \"Extase\",\"info4\":\"1933\"}]}}}";
            // String jsonInput1String =
            // "{\"notification\":{\"alert_trigger\":\"500\",\"alert_email\":\"1\",\"alert_gsm\":\"1\",\"email\":\"ano@nyme.com\",\"phone_number\":\"33983668745\"}}";

            // TypeReference<HashMap<String, Object>> typeRef = new
            // TypeReference<HashMap<String, Object>>() {
            // };
            // Map<String, Object> map = mapper.readValue(jsonInput1String, typeRef);

            InputStream initialStream = Invoice.download(29000);

			String outputFile = "//home//mathieu//output.pdf";
            Files.copy(initialStream, Paths.get(outputFile));
            // String outputFile = "//home//mathieu//output.pdf";
            // IOUtils.copy(initialStream, new FileOutputStream(outputFile));
            // Files.copy(initialStream, Paths.get(outputFile));

            // String outputFile = "//home//mathieu//output.txt";
            // Files.copy(initialStream, Paths.get(outputFile));
            //InvoiceGetReponse test = Invoice.get(42);
            //StringBuffer resultTest = new StringBuffer();

           // initialStream.close();
            // AccountUpdateRetentionResponse rep = Account.updateRetention(map);
            // AccountRetentionResponse repon = Account.retention();

            // CampaignNpaiToBlacklistResponse test = Campaign.npaiToBlacklist(70192);
            // System.out.println(test.message);
            // CampaignSendResponse test = Campaign.send(map);
            // System.out.println(test.cost);       
            // CampaignCancelResponse cancelreponse = Campaign.cancel(28818820);
            // System.out.println(cancelreponse.credits);   
            // CampaignHistoryResponse response = Campaign.history(params);
            // for(CampaignHistoryResponse.Campaign campaign:response.campaigns) {			
            //     System.out.println(campaign.id+" "+campaign.text+" "+campaign.date);
                
            // }
        }
        catch(SMSFactorException e) {
            System.out.println(e.getMessage());
            System.out.println("SMS Factor error code : " + e.getSMSFactorCode());
        }
    }
}
