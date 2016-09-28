package de.thaso.mc.fe.chat.producer;

import de.thaso.mc.be.chat.service.ChatService;
import de.thaso.mc.fe.chat.model.ChatOverviewModel;
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
public class ChatOverviewProducer {

    private static final Logger LOG = LoggerFactory.getLogger(ChatOverviewProducer.class);

    @EJB
    private ChatService chatService;

    @Produces
    @RequestScoped
    @Named("chatOverviewModel")
    public ChatOverviewModel produceChatOverviewModel(@New ChatOverviewModel chatOverviewModel) {
        LOG.info("produce chat overview model ...");
        chatOverviewModel.setSimpleMessage("Hello, i'm the controller!");
        chatOverviewModel.setLastChatMessages(chatService.findLast10ChatMessages());
        return chatOverviewModel;
    }
}
