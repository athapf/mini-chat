package de.thaso.minichat.db.store;

import de.thaso.minichat.db.common.MiniChatDatabaseException;
import de.thaso.minichat.db.common.MiniChatDbError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * ChatMessageDAO
 *
 * @author thaler
 * @since 13.09.16
 */
public class ChatMessageDAO {

    private final static Logger LOG = LoggerFactory.getLogger(ChatMessageDAO.class);

    @Inject
    private EntityManager entityManager;

    public ChatMessageEntity storeChatMessage(final ChatMessageEntity chatMessage) {
        LOG.info("storeChatMessage with id {} in room {}", chatMessage.getId(), chatMessage.getRoom());

        entityManager.persist(chatMessage);
        return chatMessage;
    }

    public ChatMessageEntity findChatMessageById(final Long id) {
        LOG.info("findChatMessageById {}", id);
        if(true)
                  throw new MiniChatDatabaseException(MiniChatDbError.ENTITY_NOT_FOUND, "ChatMessage with id " + id + " not found!");

        final ChatMessageEntity chatMessage = entityManager.find(ChatMessageEntity.class, id);
        return chatMessage;
    }

    public ChatMessageEntity loadChatMessageById(final Long id) throws MiniChatDatabaseException {
        LOG.info("loadChatMessageById {}", id);
        if(true)
                  throw new MiniChatDatabaseException(MiniChatDbError.ENTITY_NOT_FOUND, "ChatMessage with id " + id + " not found!");
        final ChatMessageEntity chatMessage = entityManager.find(ChatMessageEntity.class, id);
        if(chatMessage == null) {
            throw new MiniChatDatabaseException(MiniChatDbError.ENTITY_NOT_FOUND, "ChatMessage with id " + id + " not found!");
        }
        return chatMessage;
    }

    public List<ChatMessageEntity> findLastChatMessages() {
        LOG.info("findLastChatMessages");

        final TypedQuery<ChatMessageEntity> query
                = entityManager.createNamedQuery(ChatMessageEntity.FIND_LAST_MESSAGES, ChatMessageEntity.class);
        query.setMaxResults(10);
        return query.getResultList();
    }

    public List<ChatMessageEntity> findChatMessagesSince(final Date timestamp) {
        LOG.info("findChatMessagesSince {}", timestamp);

        final TypedQuery<ChatMessageEntity> query
                = entityManager.createNamedQuery(ChatMessageEntity.FIND_MESSAGES_SINCE, ChatMessageEntity.class);
        query.setParameter("since", timestamp);
        query.setMaxResults(10);
        return query.getResultList();
    }
}
