package de.thaso.mc.fe.chat;

import de.thaso.mc.be.chat.service.ChatMessage;
import de.thaso.mc.be.chat.service.ChatService;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import java.util.List;

/**
 * ChatController
 *
 * @author thaler
 * @since 23.09.16
 */
@Named
@ApplicationScoped
public class ChatController {

    @EJB
    private ChatService chatService;

    public String getMessage() {
        return "Hello, im the controller!";
    }

    public List<ChatMessage> readLastChatMessages() {
        return chatService.findLast10ChatMessages();
    }
}
