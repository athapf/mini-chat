package de.thaso.mc.be.chat.service;

import java.util.Date;
import java.util.List;

/**
 * ChatService
 *
 * @author thaler
 * @since 21.09.16
 */
public interface ChatService {

    void sendChatMessage(ChatMessage chatMessage);

    List<ChatMessage> findLast10ChatMessages();

    List<ChatMessage> find10ChatMessagesSince(Date timestamp);
}
