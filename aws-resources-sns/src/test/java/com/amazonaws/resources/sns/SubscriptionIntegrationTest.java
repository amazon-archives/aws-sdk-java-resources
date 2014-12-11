package com.amazonaws.resources.sns;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sns.model.InvalidParameterException;


public class SubscriptionIntegrationTest extends SNSIntegrationTestBase {

    private static Subscription subscription;

//    @BeforeClass
    public static void setUp() throws IOException {
        setUpSNS();
        subscription = getOrCreateSubscription();
        assertValidSubscription(subscription);
    }

//    @AfterClass
    public static void tearDown() {
        subscription.delete();
    }

    @Test
    @Ignore
    public void testAll() {

        for (Subscription subscription : sns.getSubscriptions().firstPage()) {
            assertValidSubscription(subscription);
            testReferences(subscription);
        }

    }

    @Test
    @Ignore
    public void testActions() {

        // setAttributes
        try {
            subscription.setAttributes("RawMessageDelivery", "false");
            refreshSubscription();
            Assert.assertEquals("false", subscription.getAttributes().get("RawMessageDelivery"));

        } catch (InvalidParameterException ipe) {
            Assert.assertTrue(ipe.getMessage().contains(
                    "delivery protocol [application] does not support raw message delivery."));
        }

    }

    public static Subscription getOrCreateSubscription() {
        for (Topic topic : sns.getTopics()) {
            for (Subscription subscription : topic.getSubscriptions()) {
                return subscription;
            }
        }

        return createNewSubscription();
    }

    public static void assertValidSubscription(final Subscription subscription) {
        Assert.assertNotNull(subscription);
        Assert.assertNotNull(subscription.getArn());
        Assert.assertNotNull(subscription.getAttributes());
        Assert.assertNotNull(subscription.getTopicArn());
    }

    private void testReferences(final Subscription subscription) {
        try {
            TopicIntegrationTest.assertValidTopic(subscription.getTopic());

        } catch (AmazonServiceException ase) {
            // It's possible that the topic has been deleted
            Assert.assertEquals("NotFound", ase.getErrorCode());
            Assert.assertEquals("Topic does not exist", ase.getErrorMessage());
        }
    }

    private static Subscription createNewSubscription() {
        Topic topic = TopicIntegrationTest.getOrCreateTopic();
        PlatformEndpoint endpoint =
                PlatformEndpointIntegrationTest.getOrCreateEndpoint();

        return topic.subscribe(endpoint.getArn(), "application");
    }

    private static void refreshSubscription() {
        if (subscription.isLoaded()) {
            throw new RuntimeException("To be added...");
        }

        // else, no need to reload
    }

}
