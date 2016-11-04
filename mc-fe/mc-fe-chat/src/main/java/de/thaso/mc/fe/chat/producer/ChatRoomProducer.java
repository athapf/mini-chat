package de.thaso.mc.fe.chat.producer;

import de.thaso.mc.be.chat.service.ChatService;
import de.thaso.mc.fe.chat.model.ChatRoomModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * ChatOverviewProducer
 *
 * @author thaler
 * @since 27.09.16
 */
public class ChatRoomProducer {

    private static final Logger LOG = LoggerFactory.getLogger(ChatRoomProducer.class);

    @EJB
    private ChatService chatService;

    @Produces
    @RequestScoped
    @Named("chatRoomModel")
    public ChatRoomModel produceChatRoomModel(@New ChatRoomModel chatRoomModel) {
        LOG.info("produce chat room model ...");
        chatRoomModel.setRoom("Sprechzimmer 1");
        chatRoomModel.setNick("me, my self and I");
        return chatRoomModel;
    }
}
