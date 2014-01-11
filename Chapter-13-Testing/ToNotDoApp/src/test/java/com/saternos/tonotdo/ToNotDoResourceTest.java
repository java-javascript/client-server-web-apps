package com.saternos.tonotdo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

import static org.junit.Assert.*;

public class ToNotDoResourceTest {

    private String url;
    private String server;
    private String securityRealm;
    private String username;
    private String password;
    private int port;
    private CloseableHttpClient client;

    /**
     * Setup Initialize variables and make a connection
     * Jetty is run automatically during mvn clean install
     * Run Jetty interactively for debugging using mvn jetty:run
     */
    @Before
    public void setUp() {
        server = "localhost";
        port = 8080;
        url = "http://" + server + ":" + port + "/api/tonotdo";
        securityRealm = "my-security-realm";
        username = "guest";
        password = "guest";

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(server, port, securityRealm), new UsernamePasswordCredentials(username, password));

        client = HttpClientBuilder.
                create().
                setDefaultCredentialsProvider(credentialsProvider).
                build();
    }

    /**
     * Close the CloseableHttpClient
     *
     * @throws IOException
     */
    @After
    public void tearDown() throws IOException {
        client.close();
    }

    /**
     * Verify a call with no credentials is rejected as unauthorized.
     *
     * @throws IOException
     */
    @Test
    public void testRequiresCredentials() throws IOException {
        CloseableHttpClient localClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        assertEquals(HttpStatus.SC_UNAUTHORIZED, localClient.execute(request).getStatusLine().getStatusCode());
    }

    /**
     * Validate that an item can be added and deleted
     *
     * @throws IOException
     */
    @Test
    public void testAddAndDeleteItem() throws IOException {
        String externalKey = "user1testAdd1";
        String json = "{\"lastUpdated\":\"01/02/2003 04:05:06\",\"description\":\"Descript 1\",\"priority\":2,\"externalKey\":\"" + externalKey + "\"}";
        addItem(json, externalKey);
        deleteItem(externalKey);
    }

    /**
     * Validate that an item can be retrieved.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testGetItem() throws IOException {
        String responseBody;
        String externalKey = "user1testGet1";
        String json = "{\"lastUpdated\":\"01/02/2003 04:05:06\",\"description\":\"Descript 1\",\"priority\":2,\"externalKey\":\"" + externalKey + "\"}";

        addItem(json, externalKey);

        HttpGet request = new HttpGet(url + "/" + externalKey);
        request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);

        // Execute the request
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        responseBody = EntityUtils.toString(entity);

        // Verify the HTTP Response Code
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        // Check a header.
        assertEquals(MediaType.APPLICATION_JSON, response.getFirstHeader(HttpHeaders.CONTENT_TYPE).getValue());

        // A quick way to verify that a JSON response with some sort of reasonably content is returned
        assertTrue(responseBody.contains("{\"lastUpdated\":"));

        // Actually parsing a JSON object using Jackson
        assertEquals(externalKey, new ObjectMapper().
                readTree(responseBody).
                findValue("externalKey").asText());

        deleteItem(externalKey);
    }

    @Test
    public void testList() throws IOException {
        String responseBody;

        for (int i = 0; i < 10; i++) {
            String externalKey = "user1testAdd" + i;
            String json = "{\"lastUpdated\":\"01/02/2003 04:05:06\",\"description\":\"Descript 1\",\"priority\":2,\"externalKey\":\"" + externalKey + "\"}";
            addItem(json, externalKey);
        }

        HttpGet request = new HttpGet(url);
        request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);

        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        responseBody = EntityUtils.toString(entity);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getFirstHeader(HttpHeaders.CONTENT_TYPE).getValue());

        JsonNode node = new ObjectMapper().readTree(responseBody);
        assertEquals(10, node.size());
    }

    /**
     * Utilty method for adding Items
     *
     * @param json
     * @param key
     * @throws IOException
     */
    private void addItem(String json, String key) throws IOException {
        String responseBody;

        HttpPut request = new HttpPut(url);
        request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        request.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        request.setEntity(new StringEntity(json));

        // Execute the request
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        responseBody = EntityUtils.toString(entity);

        // Verify the HTTP Response Code
        assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());

        // Check a header.
        assertEquals(MediaType.APPLICATION_JSON, response.getFirstHeader(HttpHeaders.CONTENT_TYPE).getValue());

        // A quick way to verify that a JSON response with some sort of reasonably content is returned
        assertThat(responseBody, JUnitMatchers.containsString("{\"lastUpdated\":"));

        // Actually parsing a JSON object using Jackson
        assertEquals(key, new ObjectMapper().
                readTree(responseBody).
                findValue("externalKey").asText());
    }

    @Test
    public void testItemLastUpdated() {
        String lastUpdated = "01/02/2003 04:05:06";
        Item i = new Item();
        i.setLastUpdated(lastUpdated);
        assertEquals(lastUpdated, i.getLastUpdated());
    }

	/* TODO - add tests for other item fields */
    // String description;
    // Integer priority;
    // String externalKey;

    /**
     * Utility method to delete an item
     *
     * @param id
     * @throws IOException
     */
    public void deleteItem(String id) throws IOException {
        HttpDelete request = new HttpDelete(url + "/" + id);
        request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        HttpResponse response = client.execute(request);
        assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusLine().getStatusCode());
    }

}

