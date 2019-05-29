import com.fasterxml.jackson.databind.ObjectMapper;
import com.smsfactor.SMSFactor;
import com.smsfactor.model.*;
import com.smsfactor.response.*;
import com.smsfactor.exception.SMSFactorException;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class SMSFactorTests {
    private static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMTEyNCIsImlhdCI6MTU1NjAxMDEyNX0.mvbtwke3ji2UZ_npySJ-LTepr5NEud9BIdtBT68RgXQ";

    public static void setToken(String token) {
        SMSFactor.apiToken = token;
    }


    @Test
    public void testGetCredits() {
        setToken(token);
        AccountCreditsResponse response = null;
        try {
            response = Account.credits();
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }
        String body = response.getBody();
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertNotEquals(new Long(body.indexOf("credits")), new Long(-1));
    }

    @Test
    public void testGetAccount() {
        setToken(token);
        AccountGetResponse response = null;
        try {
            response = Account.get();
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertNotEquals(new Long(body.indexOf("account")), new Long(-1));
    }

    @Test
    public void testGetSubAccounts() {
        setToken(token);
        AccountSubAccountsResponse response = null;
        try {
            response = Account.subAccounts();
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertNotEquals(new Long(body.indexOf("sub-accounts")), new Long(-1));
    }

    @Test
    public void testCreateAccount() {
        setToken(token);

        Map<String, String> user = new HashMap<String, String>();
        user.put("firstname", "George");
        user.put("lastname", "Abitbol");
        user.put("city", "Pluvigner");
        user.put("phone", "0011223344");
        user.put("address1", "3 avenue du président Coty");
        user.put("zip", "56330");
        user.put("country_code", "fr");
        user.put("isChild", "0");
        user.put("unlimited", "0");
        user.put("email", "g.abitbol+" + UUID.randomUUID().toString() + "@smsfactor.com");
        user.put("password", "edR3meDeDn0m");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("account", user);

        AccountCreateResponse response = null;
        try {
            response = Account.create(params);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertNotEquals(new Long(body.indexOf("id")), new Long(-1));
    }

    /** Campaign **/
    @Test
    public void testCampaignSend() throws IOException {
        setToken(token);

        Map<String, String> message = new HashMap<String, String>();
        message.put("text", "test skd java");
        message.put("pushtype", "alert");
        message.put("sender", "SDK");

        List<Map<String, String>> gsm = new ArrayList<Map<String, String>>();
        gsm.add(
                new HashMap<String, String>() {{
                    put("gsmsmsid", "100");
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

        System.out.println(payload);

        CampaignSendResponse response = null;
        try {
            response = Campaign.send(payload, false);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertNotEquals(new Long(body.indexOf("ticket")), new Long(-1));
        testCampaignGet(response.ticket);
        testCampaignCancel(response.ticket);
    }

    /**
     * @depends testCampaignSend
     */
    public void testCampaignGet(Integer ticket) throws IOException {
        CampaignGetResponse response = null;
        try {
            response = Campaign.get(ticket);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertNotEquals(new Long(body.indexOf("campaign")), new Long(-1));
    }

    /**
     * @depends testCampaignSend
     */
    public void testCampaignCancel(Integer ticket) throws IOException {
        CampaignCancelResponse response = null;
        try {
            response = Campaign.cancel(ticket);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertNotEquals(new Long(body.indexOf("credits")), new Long(-1));
    }

   @Test
    public void testCampaignHistory() throws IOException {
        setToken(token);

        Map<String, Object> lenght = new HashMap<String, Object>();
        lenght.put("length", 1);
        CampaignHistoryResponse response = null;
        try {
            response = Campaign.history(lenght);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertNotEquals(new Long(body.indexOf("campaigns")), new Long(-1));
    }

   @Test
    public void testCreateList() {
        setToken(token);

        List<Map<String, String>> gsm = new ArrayList<Map<String, String>>();
        gsm.add(
                new HashMap<String, String>() {{
                    put("value", "33600000001");
                    put("info1", "contact 1");
                }}
        );
        gsm.add(
                new HashMap<String, String>() {{
                    put("value", "33600000002");
                    put("info1", "contact 2");
                }}
        );

        Map<String, Object> contacts = new HashMap<String, Object>();
        contacts.put("gsm", gsm);

        Map<String, Object> object = new HashMap<String, Object>();
        object.put("name", "sdk list");
        object.put("contacts", contacts);

        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("list", object);

        ContactListCreateResponse response = null;
        try {
            response = ContactList.create(payload);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }
        String body = response.getBody();

        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertNotEquals(new Long(body.indexOf("contacts")), new Long(-1));
        Assert.assertNotEquals(new Long(body.indexOf("id")), new Long(-1));
        testGetList(response.id);
        testAddContactToList(response.id);
        testCampaignSendToLists(response.id);
    }

    /**
     * @depends testCreateList
     */
    public void testAddContactToList(Integer list_id) {
       List<Map<String, String>> gsm = new ArrayList<Map<String, String>>();
        gsm.add(
                new HashMap<String, String>() {{
                    put("value", "33600000001");
                    put("info1", "contact 1");
                }}
        );
        gsm.add(
                new HashMap<String, String>() {{
                    put("value", "33600000002");
                    put("info1", "contact 2");
                }}
        );

        Map<String, Object> contacts = new HashMap<String, Object>();
        contacts.put("gsm", gsm);

        Map<String, Object> object = new HashMap<String, Object>();
        object.put("list", list_id);
        object.put("contacts", contacts);

        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("list", object);

        ContactListAddContactsResponse response = null;
        try {
            response = ContactList.addContacts(payload);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertNotEquals(new Long(body.indexOf("contacts")), new Long(-1));
        Assert.assertNotEquals(new Long(body.indexOf("id")), new Long(-1));
        testDeduplicateList(list_id);
    }

    /**
     * @depends testAddContactToList
     */
    public void testCampaignSendToLists(Integer list_id) {
        Map<String, String> message = new HashMap<String, String>();
        message.put("text", "test skd java");
        message.put("pushtype", "alert");
        message.put("sender", "SDK");

        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        lists.add(
                new HashMap<String, String>() {{
                    put("value", "33600000001");
                }}
        );
        lists.add(
                new HashMap<String, String>() {{
                    put("value", "33600000002");
                }}
        );

        Map<String, Object> sms = new HashMap<String, Object>();
        sms.put("message", message);
        sms.put("lists", lists);

        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("sms", sms);

        CampaignSendResponse response = null;
        try {
            response = Campaign.sendToLists(payload, true);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertNotEquals(new Long(body.indexOf("ticket")), new Long(-1));
    }

    /**
     * @depends testCampaignSendToLists
     */
    public void testGetList(Integer list_id) {
        ContactListGetResponse response = null;
        try {
            response = ContactList.get(list_id);
            String body = response.getBody();
            Assert.assertEquals(new Long(1), new Long(response.status));
            Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
            Assert.assertNotEquals(new Long(body.indexOf("list")), new Long(-1));

            String id = response.list.get(0).id;
            testRemoveContact(id);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }
    }

    /**
     * @depends testGetList
     */
    public void testRemoveContact(String contact_id) {
        ContactListRemoveContactResponse response = null;
        try {
            response = ContactList.removeContact(contact_id);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
    }

    /**
     * @depends testAddContactToList
     */
    public void testDeduplicateList(Integer list_id) {
        ContactListDeduplicateResponse response = null;
        try {
            response = ContactList.deduplicate(list_id);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertNotEquals(new Long(body.indexOf("removed")), new Long(-1));
        testRemoveList(list_id);
    }

    @Test
    public void testGetLists() throws IOException {
        setToken(token);

        ContactListAllResponse response = null;
        try {
            response = ContactList.all();
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertNotEquals(new Long(body.indexOf("lists")), new Long(-1));
    }

    /**
     * @depends testDeduplicateList
     */
    public void testRemoveList(Integer list_id) {
        ContactListDeleteResponse response = null;
        try {
            response = ContactList.delete(list_id);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
    }

    @Test
    public void testAddToBlacklist() {
        setToken(token);

        List<Map<String, String>> gsm = new ArrayList<Map<String, String>>();
        gsm.add(
                new HashMap<String, String>() {{
                    put("value", "33600000001");
                    put("info1", "contact 1");
                }});
        gsm.add(
                new HashMap<String, String>() {{
                    put("value", "33600000002");
                    put("info1", "contact 2");
                }});

        Map<String, Object> contacts = new HashMap<String, Object>();
        contacts.put("gsm", gsm);

        Map<String, Object> object = new HashMap<String, Object>();
        object.put("contacts", contacts);

        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("blacklist", object);

        ContactListAddToBlacklistResponse response = null;
        try {
            response = ContactList.addToBlacklist(payload);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertEquals(new Long(2), new Long(response.added_contacts));
        Assert.assertNotEquals(new Long(body.indexOf("added_contacts")), new Long(-1));
    }

    @Test
    public void testGetBlacklist() {
        setToken(token);

        ContactListBlacklistResponse response = null;
        try {
            response = ContactList.blacklist();
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertNotEquals(new Long(body.indexOf("list")), new Long(-1));
    }

    @Test
    public void testAddToNPAI() {
        setToken(token);

        List<Map<String, String>> gsm = new ArrayList<Map<String, String>>();
        gsm.add(
                new HashMap<String, String>() {{
                    put("value", "33600000017");
                    put("info1", "contact 1");
                }});
        gsm.add(
                new HashMap<String, String>() {{
                    put("value", "33600000018");
                    put("info1", "contact 2");
                }});

        Map<String, Object> contacts = new HashMap<String, Object>();
        contacts.put("gsm", gsm);

        Map<String, Object> object = new HashMap<String, Object>();
        object.put("contacts", contacts);

        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("npai", object);

        ContactListAddToNpaiResponse response = null;
        try {
            response = ContactList.addToNpai(payload);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertEquals(new Long(2), new Long(response.added_contacts));
        Assert.assertNotEquals(new Long(body.indexOf("added_contacts")), new Long(-1));
    }

    @Test
    public void testGetNPAI() {
        setToken(token);

        ContactListNpaiResponse response = null;
        try {
            response = ContactList.npai();
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertNotEquals(new Long(body.indexOf("list")), new Long(-1));
    }


    /** Message **/

    @Test
    public void testSendMessage() {
        setToken(token);

         Map<String, Object> params = new HashMap<String, Object>();
         params.put("text", "test");
         params.put("to", "33601000000");


        MessageSendResponse response = null;
        try {
            response = Message.send(params);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertNotEquals(new Long(body.indexOf("ticket")), new Long(-1));
    }

    /** Token **/

    @Test
    public void testCreateToken() {
        setToken(token);

        Map<String, String> token = new HashMap<String, String>();
        token.put("name", "token sdk");

        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("token", token);

        TokenCreateResponse response = null;
        try {
            response = Token.create(payload);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertNotEquals(new Long(body.indexOf("token")), new Long(-1));
        Assert.assertNotEquals(new Long(body.indexOf("token_id")), new Long(-1));
        testDeleteToken(response.token_id);
    }

    @Test
    public void testGetTokens() {
        setToken(token);

        TokenAllResponse response = null;
        try {
            response = Token.all();
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertNotEquals(new Long(body.indexOf("tokens")), new Long(-1));
    }

    /**
     * @depends testCreateToken
     */
    public void testDeleteToken(Integer token_id) {
       TokenDeleteResponse response = null;
        try {
            response = Token.delete(token_id);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertNotEquals(new Long(body.indexOf("deleted_token")), new Long(-1));
    }

    /** Webhook **/
   @Test
    public void testCreateWebhook() {
        setToken(token);

        Map<String, String> webhook = new HashMap<String, String>();
        webhook.put("type", "DLR");
        webhook.put("url", "https://yourserverurl.com");

        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("webhook", webhook);

        WebhookCreateResponse response = null;
        try {
            response = Webhook.create(payload);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertNotEquals(new Long(body.indexOf("webhook")), new Long(-1));
        Assert.assertNotEquals(new Long(body.indexOf("webhook_id")), new Long(-1));
        testUpdateWebhook(response.webhook.webhook_id);
    }

    /**
     * @depends testCreateWebhook
     */
    public void testUpdateWebhook(Integer webhook_id) {
        Map<String, String> webhook = new HashMap<String, String>();
        webhook.put("type", "MO");
        webhook.put("url", "https://yourserverurl.com");

        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("webhook", webhook);

        WebhookUpdateResponse response = null;
        try {
            response = Webhook.update(webhook_id, payload);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertNotEquals(new Long(body.indexOf("webhook")), new Long(-1));
        Assert.assertNotEquals(new Long(body.indexOf("webhook_id")), new Long(-1));
        testDeleteWebhook(response.webhook.webhook_id);
    }

    @Test
    public void testGetWebhooks() {
        setToken(token);

        WebhookAllResponse response = null;
        try {
            response = Webhook.all();
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertNotEquals(new Long(body.indexOf("webhooks")), new Long(-1));
    }

    /**
     * @depends testUpdateWebhook
     */
    public void testDeleteWebhook(Integer webhook_id) {
       WebhookDeleteResponse response = null;
        try {
            response = Webhook.delete(webhook_id);
        } catch (SMSFactorException e) {
            e.printStackTrace();
        }

        String body = response.getBody();
        Assert.assertEquals(new Long(200), new Long(response.getStatusCode()));
        Assert.assertEquals(new Long(1), new Long(response.status));
        Assert.assertNotEquals(new Long(body.indexOf("deleted_id")), new Long(-1));
    }

    @Test
    public void testErrorGetters() {
        SMSFactor.apiToken = "1";
        AccountCreditsResponse response = null;
        try {
            response = Account.credits();
        } catch (SMSFactorException e) {
            Assert.assertEquals(new Long(-1), new Long(e.getSMSFactorCode()));
            Assert.assertEquals(new Long(200), new Long(e.getHttpStatusCode()));
            Assert.assertTrue(e.getHttpBody() instanceof Object);
        }
    }
}
