package de.thaso.mc.be.chat.service;

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
}
