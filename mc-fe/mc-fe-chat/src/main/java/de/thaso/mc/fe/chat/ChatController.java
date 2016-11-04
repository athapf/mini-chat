package de.thaso.mc.fe.chat;

import de.thaso.mc.fe.chat.model.ChatOverviewModel;
import de.thaso.mc.fe.chat.model.ChatRoomModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * ChatController
 *
 * @author thaler
 * @since 23.09.16
 */
@Named
@RequestScoped
public class ChatController {

    private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);

    @Inject
    private ChatRoomModel chatRoomModel;

    @Inject
    private ChatOverviewModel chatOverviewModel;

    public void sendChatMessage() {
        LOG.info("send message: {}", chatRoomModel.getMessage());
    }

    public void clear() {
        chatRoomModel.setMessage("");
    }
}
