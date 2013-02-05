package com.force;
import com.sforce.ws.*;
import com.sforce.soap.partner.*;
import com.sforce.soap.partner.sobject.*;



public class PartnerClient {

    private String username = "sebastiangnagnarella@demo.com";
    private String password = "Neme1507oNqR76lmqOYABImJYMgUX2jOZ";
    private PartnerConnection connection;

    public PartnerClient() throws ConnectionException {
        ConnectorConfig config = new ConnectorConfig();
        config.setUsername(username);
        config.setPassword(password);
        connection = Connector.newConnection(config);
        createAccount();
        
    }

    private void createAccount() throws ConnectionException {
        SObject account = new SObject();
        account.setType("Account");
        account.setField("Name", "My Account");
        account.setField("Phone", "123 244 3455");
        connection.create(new SObject[]{account});
    }

    public static void main(String[] args) throws ConnectionException {
        new PartnerClient();
    }
    

}
