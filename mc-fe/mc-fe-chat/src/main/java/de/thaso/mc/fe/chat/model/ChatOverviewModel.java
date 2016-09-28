package de.thaso.mc.fe.chat.model;

import de.thaso.mc.be.chat.service.ChatMessage;

import javax.enterprise.inject.Any;
import java.io.Serializable;
import java.util.List;

/**
 * ChatOverviewModel
 *
 * @author thaler
 * @since 27.09.16
 */
@Any
public class ChatOverviewModel implements Serializable {

    private String simpleMessage;
    private List<ChatMessage> lastChatMessages;

    public String getSimpleMessage() {
        return simpleMessage;
    }

    public void setSimpleMessage(final String simpleMessage) {
        this.simpleMessage = simpleMessage;
    }

    public List<ChatMessage> getLastChatMessages() {
        return lastChatMessages;
    }

    public void setLastChatMessages(final List<ChatMessage> lastChatMessages) {
        this.lastChatMessages = lastChatMessages;
    }
}
