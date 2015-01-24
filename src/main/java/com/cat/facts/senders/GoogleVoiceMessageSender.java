package com.cat.facts.senders;

import com.techventus.server.voice.Voice;
import java.io.IOException;
import java.util.List;

/**
 * Send messages using Google Voice.
 *
 * @author Steve
 */
public class GoogleVoiceMessageSender implements MessageSender {

    private final Voice voice;

    /**
     * Constructor initializes Google Voice instance.
     *
     * @param username
     * @param password
     * @throws java.io.IOException
     */
    public GoogleVoiceMessageSender(String username, String password) throws IOException {
        voice = new Voice(username, password);
    }

    /**
     * Text a single recipient a message.
     *
     * @param recipient phone number
     * @param message message to text
     */
    @Override
    public void send(String recipient, String message) throws IOException {
        voice.sendSMS(recipient, message);
    }

    /**
     * Text multiple recipients the same message.
     *
     * @param recipients
     * @param message
     */
    @Override
    public void send(List<String> recipients, String message) throws IOException {
        for (String recipient : recipients) {
            voice.sendSMS(recipient, message);
        }
    }

}
