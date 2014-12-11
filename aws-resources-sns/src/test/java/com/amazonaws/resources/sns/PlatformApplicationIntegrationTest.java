package com.amazonaws.resources.sns;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sns.model.CreatePlatformApplicationRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.DeletePlatformApplicationRequest;
import com.amazonaws.services.sns.model.SetPlatformApplicationAttributesRequest;


public class PlatformApplicationIntegrationTest extends SNSIntegrationTestBase {

    static final String GCM_API_ID = "AIzaSyD-4pBAk6M7eveE9dwRFyGv-cfYBPiHRmk";
    static final String GCM_DEVICE_TOKEN =
            "APA91bHXHl9bxaaNvHHWNXKwzzaeAjJnBP3g6ieaGta1aPMgrilr0H-QL4AxUZUJ-1mk0gnLpmeXF0Kg7-9fBXfXHTKzPGlCyT6E6oOfpdwLpcRMxQp5vCPFiFeru9oQylc22HvZSwQTDgmmw9WdNlXMerUPzmoX0w";

    private static PlatformApplication application;

//    @BeforeClass
    public static void setUp() throws IOException {
        setUpSNS();
        application = getOrCreateApplication();
        assertValidApplication(application);
    }

//    @AfterClass
    public static void tearDown() {
        // delete
        application.delete(new DeletePlatformApplicationRequest());
    }

    @Test
    @Ignore
    public void testAll() {

        for (PlatformApplication application : sns.getPlatformApplications()) {
            assertValidApplication(application);
            testReferences(application);
        }

    }

    @Test
    @Ignore
    public void testActions() throws InterruptedException {

        String topicArn = TopicIntegrationTest.getOrCreateTopic().getArn();

        // setAttributes
        application.setAttributes(new SetPlatformApplicationAttributesRequest()
            .addAttributesEntry("EventEndpointCreated", topicArn)
            .addAttributesEntry("EventEndpointDeleted", topicArn)
            .addAttributesEntry("EventEndpointUpdated", topicArn)
            );
        refreshApplication();
        Assert.assertEquals(topicArn, application.getAttributes().get("EventEndpointCreated"));
        Assert.assertEquals(topicArn, application.getAttributes().get("EventEndpointDeleted"));
        Assert.assertEquals(topicArn, application.getAttributes().get("EventEndpointUpdated"));


        // createPlatformEndpoint
        application.createPlatformEndpoint(new CreatePlatformEndpointRequest()
            .withToken(GCM_DEVICE_TOKEN)
            .withCustomUserData("My custom user data")
            );

    }

    public static PlatformApplication getOrCreateApplication() {
        for (PlatformApplication application : sns.getPlatformApplications()) {
            try {
                // make sure the the application actually exists
                application.load();
                return application;
            } catch (AmazonServiceException ase) {
                // continue
            }

        }

        return createNewApplication();
    }

    public static void assertValidApplication(final PlatformApplication application) {
        Assert.assertNotNull(application);
        Assert.assertNotNull(application.getArn());
        Assert.assertNotNull(application.getAttributes());
        Assert.assertNotNull(application.getEndpoints());
    }

    private void testReferences(final PlatformApplication application) {

        for (PlatformEndpoint endpoint : application.getEndpoints()) {
            PlatformEndpointIntegrationTest.assertValidEndpoint(endpoint);
        }

    }

    private static PlatformApplication createNewApplication() {
        String applicationName = "java-sns-resources-api-app-" + System.currentTimeMillis();

        PlatformApplication application = sns.createPlatformApplication(
                new CreatePlatformApplicationRequest()
                    .withName(applicationName)
                    .withPlatform("GCM")
                    .addAttributesEntry("PlatformCredential", GCM_API_ID)
                    );

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {}

        return application;
    }

    private static void refreshApplication() {
        application = sns.getPlatformApplication(application.getArn());
    }

}

