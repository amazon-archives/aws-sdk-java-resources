package com.amazonaws.resources.sqs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.resources.ServiceBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageBatchRequest;
import com.amazonaws.services.sqs.model.DeleteMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;
import com.amazonaws.services.sqs.model.SendMessageBatchResultEntry;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.amazonaws.util.ImmutableMapParameter;

/**
 * Integration tests for SQS Resources code.
 *
 */
public class SQSResourcesIntegrationTest {

    /** Prefix to be used for Queue names. */
    private static final String QUEUE_NAME_PREFIX = "java-sdk-";

    /** The name of the sqs queue used for testing. */
    private static final String QUEUE_NAME = QUEUE_NAME_PREFIX + "resources-queue-"
            + System.currentTimeMillis();

    /**
     * Reference to the queue resource.
     */
    private static Queue queue = null;

    /**
     * Reference to the service SQS
     */
    protected static SQS sqs = null;

    /**
     * message content used for testing.
     */
    private static final String TEST_MESSAGE = "java-sdk-resources-test-message";

    /**
     * message content used for testing wth attributes set to message.
     */
    private static final String TEST_MESSAGE_ATTRIBUTES = "java-sdk-resources-test-message-attributes";

    /**
     * Waiting time for receiving messages from queue.
     */
    private static final long SLEEP_TIME_MILLIS = 5000;

    /**
     * Creates a new Service builder for SQS and uses it to create to Queue.
     * Asserts that the queue created is loaded and has an Arn associated with
     * it.
     */
//    @BeforeClass
    public static void setUp() {

        sqs = ServiceBuilder.forService(SQS.class)
                .withCredentials(new ProfileCredentialsProvider()).build();

        setUpNewQueue();
    }

    /**
     * Deletes the queue.
     */
//    @AfterClass
    public static void tearDown() {
        if (queue != null) {
            queue.delete();
        }
    }

    /**
     * Sets up a new queue and loads it. Asserts that the loaded queue has an
     * Arn associated with it.
     */
    private static void setUpNewQueue() {
        queue = sqs.createQueue(QUEUE_NAME);

        assertNotNull(queue);
        assertNotNull(queue.getUrl());
        assertFalse(queue.isLoaded());
        assertTrue(queue.load());

        Map<String, String> queueAttributes = queue.getAttributes();
        assertNotNull(queueAttributes);
        assertNotNull(queueAttributes.get("QueueArn"));
    }

    /**
     * Tests the actions, collections associated that can be initiated from SQS
     * service builder.
     */
    @Test
    @Ignore
    public void testSQSResource() {
        QueueCollection queues = sqs.getQueues();
        assertNotNull(queues);
        assertTrue(queues.firstPage().size() > 0);

        Queue queueByName = sqs.getQueueByName(QUEUE_NAME);
        assertNotNull(queueByName);
        assertEquals(queue.getUrl(), queueByName.getUrl());

        assertNotNull(sqs.getQueue(queue.getUrl()));

        QueueCollection deadLetterQueues = queue.getDeadLetterSourceQueues();
        assertTrue(deadLetterQueues.firstPage().size() == 0);

        QueueCollection queuesByPrefix = sqs.getQueues(QUEUE_NAME_PREFIX);
        assertTrue(queuesByPrefix.firstPage().size() > 0);
    }

    /**
     * Asserts the message contents and its associated parameters with the given
     * message.
     */
    private void assertMessage(String expectedMessageBody,
            String expectedMessageId, String expectedMD5, Message message) {
        assertEquals(expectedMessageId, message.getMessageId());
        assertEquals(expectedMessageBody, message.getBody());
        assertEquals(expectedMD5, message.getMD5OfBody());
    }

    /**
     * Tests a simple send, receive and delete of a message from the queue
     * resource. Asserts the message contents and its associated attributes.
     */
    @Test
    @Ignore
    public void testSendReceiveDelete() throws InterruptedException {
        SendMessageResult sendMessageResult = queue.sendMessage(TEST_MESSAGE);
        assertNotNull(sendMessageResult);
        assertNotNull(sendMessageResult.getMessageId());

        List<Message> messages = waitForMessagesFromQueue(null);

        assertNotNull(messages);
        assertEquals(1, messages.size());
        Message message = messages.get(0);
        assertMessage(TEST_MESSAGE, sendMessageResult.getMessageId(),
                sendMessageResult.getMD5OfMessageBody(), message);

        queue.deleteMessages(new DeleteMessageBatchRequest()
                .withEntries(new DeleteMessageBatchRequestEntry("msg1", message
                        .getReceiptHandle())));
    }

    /**
     * Tests sending of message with message attributes. Asserts that the
     * message received has the attributes. Also changes the visibility of the
     * messages and tries to retrieve them. Performs delete action on the
     * message to the delete it from the queue.
     */
    @Test
    @Ignore
    public void testSendReceiveMessageAttributes() throws InterruptedException {

        SendMessageResult sendMessageResult = queue
                .sendMessage(new SendMessageRequest().withMessageBody(
                        TEST_MESSAGE_ATTRIBUTES).withMessageAttributes(
                        ImmutableMapParameter.of(
                                "testAttribute",
                                new MessageAttributeValue().withDataType(
                                        "String").withStringValue(
                                        "testAttributeValue"))));

        List<Message> messages = waitForMessagesFromQueue(new ReceiveMessageRequest()
                .withMessageAttributeNames("testAttribute"));

        assertNotNull(messages);
        assertEquals(1, messages.size());
        Message message = messages.get(0);
        assertMessage(TEST_MESSAGE_ATTRIBUTES,
                sendMessageResult.getMessageId(),
                sendMessageResult.getMD5OfMessageBody(), message);

        Map<String, MessageAttributeValue> messageAttributes = message
                .getMessageAttributes();
        assertNotNull(messageAttributes);
        assertTrue(messageAttributes.containsKey("testAttribute"));
        assertEquals(messageAttributes.get("testAttribute").getStringValue(),
                "testAttributeValue");

        message.changeVisibility(10);

        messages = waitForMessagesFromQueue(null);
        message.delete();
    }

    /**
     * Tests sending messages using batch operation and retrieve them. Also
     * tests setting the queue attributes and retrieving them.
     */
    @Test
    @Ignore
    public void testQueueSubResourceAndAttributes() throws InterruptedException {

        /**
         * Trying to get the message which is deleted. Here there is no service
         * call made, a new sub resource is created with the given handle. So,
         * this wont be returning null.
         */
        Message message = queue.getMessage("invalid-recepient-handle");
        assertNotNull(message);
        try {
            message.getAttributes();
            fail("An unsupported operation exception must be thrown as load operation is no supported on message attribute");
        } catch (UnsupportedOperationException use) { }

        SendMessageBatchResult sendMessageBatchResult = queue
                .sendMessages(new SendMessageBatchRequest()
                        .withEntries(new SendMessageBatchRequestEntry("msg1",
                                TEST_MESSAGE)));
        SendMessageBatchResultEntry sendMessageBatchResultEntry = sendMessageBatchResult
                .getSuccessful().get(0);
        List<Message> messages = waitForMessagesFromQueue(null);

        assertNotNull(messages);
        assertEquals(1, messages.size());
        message = messages.get(0);
        assertMessage(TEST_MESSAGE, sendMessageBatchResultEntry.getMessageId(),
                sendMessageBatchResultEntry.getMD5OfMessageBody(), message);

        queue.setAttributes(ImmutableMapParameter.of("MaximumMessageSize",
                "2048"));

        assertTrue(queue.getAttributes().containsKey("MaximumMessageSize"));
    }

    /**
     * Waits for a message from queue.
     */
    private List<Message> waitForMessagesFromQueue(ReceiveMessageRequest request)
            throws InterruptedException {
        if (request == null) {
            request = new ReceiveMessageRequest().withAttributeNames("All");
        }
        List<Message> messages = queue.receiveMessages(request);
        while (messages == null || messages.size() == 0) {
            Thread.sleep(SLEEP_TIME_MILLIS);
            messages = queue.receiveMessages(request);
        }
        return messages;
    }
}
