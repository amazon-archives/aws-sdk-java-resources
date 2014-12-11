package com.amazonaws.resources.sns;

import java.io.IOException;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sns.model.ConfirmSubscriptionRequest;

public class TopicIntegrationTest extends SNSIntegrationTestBase {

    private static Topic topic;

    private static final String DISPLAY_NAME = "DisplayName";
    private static final String MY_FANCY_TOPIC_NAME = "foooooooooooooooo";

    @BeforeClass
    public static void setUp() throws IOException {
        setUpSNS();
        topic = getOrCreateTopic();
        assertValidTopic(topic);
    }

    public static Topic getOrCreateTopic() {
        if ( !sns.getTopics().iterator().hasNext() ) {
            return createNewTopic();

        } else {
            return sns.getTopics().iterator().next();
        }
    }

    public static void assertValidTopic(final Topic topic) {
        Assert.assertNotNull(topic);
        Assert.assertNotNull(topic.getArn());
        Assert.assertNotNull(topic.getAttributes());
    }

    @Test
    public void testAll() {

        for (Topic topic : sns.getTopics()) {
            assertValidTopic(topic);
            testReferences(topic);
        }

    }

    private void testReferences(final Topic topic) {

        // sub-resource

        Subscription unloadedSubscription = topic.getSubscription("foo");
        Assert.assertFalse(unloadedSubscription.isLoaded());

        // hasMany

        for (Subscription subscription : topic.getSubscriptions()) {
            SubscriptionIntegrationTest.assertValidSubscription(subscription);
            checkBackrefFromSubscriptionToTopic(subscription, topic);
        }

    }

    @Test
    public void testActions() throws InterruptedException {

        // publish
        topic.publish("subject", "message");

        // setAttribute
        topic.setAttributes(DISPLAY_NAME, MY_FANCY_TOPIC_NAME);
        refreshTopic();
        Assert.assertEquals(MY_FANCY_TOPIC_NAME, topic.getAttributes().get(DISPLAY_NAME));

        // addPermission
        topic.addPermission("New Publish Permission", Arrays.asList("599169622985"), Arrays.asList("Publish"));

        // removePermission
        topic.removePermission("New Publish Permission");

        PlatformEndpoint endpoint =
                PlatformEndpointIntegrationTest.getOrCreateEndpoint();

        // subscribe
        Subscription subscription = topic.subscribe(endpoint.getArn(), "application");
        SubscriptionIntegrationTest.assertValidSubscription(subscription);

        // confirmSubscription
        // send a null token and expect a ValidationError
        try {
            topic.confirmSubscription(new ConfirmSubscriptionRequest());
            Assert.fail("ValidationError is expected.");
        } catch (AmazonServiceException ase) {
            Assert.assertEquals("ValidationError", ase.getErrorCode());
        }

    }

    private static void checkBackrefFromSubscriptionToTopic(Subscription subscription, Topic topic) {
        Assert.assertEquals(
                topic.getArn(),
                subscription.getTopic().getArn());
    }

    private static Topic createNewTopic() {
        throw new RuntimeException("To be added...");
    }

    private static void refreshTopic() {
        topic = sns.getTopic(topic.getArn());
    }
}
