package de.thaso.mc.be.chat.business;

import de.thaso.mc.be.chat.business.mapper.ChatMessageMapper;
import de.thaso.mc.be.chat.service.ChatMessage;
import de.thaso.mc.be.chat.service.ChatService;
import de.thaso.minichat.db.store.ChatMessageDAO;
import de.thaso.minichat.db.store.ChatMessageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * ChatServiceImpl
 *
 * @author thaler
 * @since 21.09.16
 */
@Stateless
@Remote(ChatService.class)
public class ChatServiceImpl implements ChatService {

    public static final Logger LOG = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Inject
    private ChatMessageDAO chatMessageDAO;

    @Inject
    private ChatMessageMapper chatMessageMapper;

    @Override
    public void sendChatMessage(final ChatMessage chatMessage) {
        final ChatMessageEntity chatMessageEntity = chatMessageMapper.chatMessageToEntity(chatMessage);
        chatMessageDAO.storeChatMessage(chatMessageEntity);
    }

    @Override
    public List<ChatMessage> findLast10ChatMessages() {
        LOG.debug("findLast10ChatMessages ...");
        final List<ChatMessageEntity> lastChatMessageList = chatMessageDAO.findLastChatMessages();
        final List<ChatMessage> chatMessageList = chatMessageMapper.chatMessageListToDOList(lastChatMessageList);
        LOG.debug(" ... found {} messages", chatMessageList.size());
        return chatMessageList;
    }

    @Override
    public List<ChatMessage> find10ChatMessagesSince(final Date timestamp) {
        LOG.debug("find10ChatMessagesSince({})", timestamp);
        final List<ChatMessageEntity> chatMessageList = chatMessageDAO.findChatMessagesSince(timestamp);
        return chatMessageMapper.chatMessageListToDOList(chatMessageList);
    }
}
