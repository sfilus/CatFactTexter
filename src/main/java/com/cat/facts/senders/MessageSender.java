package com.cat.facts.senders;

import java.io.IOException;
import java.util.List;

/**
 * Interface defining classes that are capable of sending messages.
 *
 * @author Steve
 */
public interface MessageSender {

    /**
     * Send a message to a single recipient.
     *
     * @param recipient string
     * @param message string
     * @throws java.io.IOException
     */
    public void send(String recipient, String message) throws IOException;

    /**
     * Send the same message to multiple recipients.
     *
     * @param recipients
     * @param message
     * @throws java.io.IOException
     */
    public void send(List<String> recipients, String message) throws IOException;
}
