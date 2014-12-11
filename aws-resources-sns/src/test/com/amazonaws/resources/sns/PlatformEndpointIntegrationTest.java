package com.amazonaws.resources.sns;

import java.io.IOException;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.DeleteEndpointRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SetEndpointAttributesRequest;


public class PlatformEndpointIntegrationTest extends SNSIntegrationTestBase {

    private static PlatformEndpoint endpoint;

    @BeforeClass
    public static void setUp() throws IOException {
        setUpSNS();
        endpoint = getOrCreateEndpoint();
        assertValidEndpoint(endpoint);
    }

    @AfterClass
    public static void tearDown() {
        endpoint.delete(new DeleteEndpointRequest());
    }

    @Test
    public void testAll() {

        for (PlatformEndpoint endpoint : PlatformApplicationIntegrationTest
                .getOrCreateApplication().getEndpoints()) {
            assertValidEndpoint(endpoint);
        }

    }

    @Test
    public void testActions() {

        // setAttribtues
        String userData = UUID.randomUUID().toString();
        endpoint.setAttributes(new SetEndpointAttributesRequest()
                .addAttributesEntry("CustomUserData", userData)
                );
        refreshEndpoint();
        Assert.assertEquals(userData, endpoint.getAttributes().get("CustomUserData"));

        // publish
        endpoint.publish(new PublishRequest()
                .withSubject("subject")
                .withMessage("message")
                );
    }

    public static PlatformEndpoint getOrCreateEndpoint() {
        PlatformApplication application =
                PlatformApplicationIntegrationTest.getOrCreateApplication();

        for (PlatformEndpoint endpoint : application.getEndpoints()) {
            try {
                // make sure the the endpoint actually exists
                endpoint.load();
                return endpoint;
            } catch (AmazonServiceException ase) {
                // continue
            }
        }

        PlatformEndpoint endpoint =  application.createPlatformEndpoint(
                new CreatePlatformEndpointRequest()
                    .withToken(PlatformApplicationIntegrationTest.GCM_DEVICE_TOKEN)
                    .withCustomUserData("My custom user data")
                    );

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {}

        return endpoint;
    }

    public static void assertValidEndpoint(final PlatformEndpoint endpoint) {
        Assert.assertNotNull(endpoint);
        Assert.assertNotNull(endpoint.getArn());
        Assert.assertNotNull(endpoint.getAttributes());
    }

    private static void refreshEndpoint() {
        endpoint = sns.getPlatformEndpoint(endpoint.getArn());
    }

}
