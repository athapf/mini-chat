package de.thaso.minichat.db.store;

import de.thaso.minichat.db.common.MiniChatDatabaseException;
import de.thaso.minichat.db.common.MiniChatDbError;

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

    @Inject
    private EntityManager entityManager;

    public ChatMessageEntity storeChatMessage(final ChatMessageEntity chatMessage) {
        entityManager.persist(chatMessage);
        return chatMessage;
    }

    public ChatMessageEntity findChatMessageById(final Long id) {
        final ChatMessageEntity chatMessage = entityManager.find(ChatMessageEntity.class, id);
        return chatMessage;
    }

    public ChatMessageEntity loadChatMessageById(final Long id) throws MiniChatDatabaseException {
        final ChatMessageEntity chatMessage = entityManager.find(ChatMessageEntity.class, id);
        if(chatMessage == null) {
            throw new MiniChatDatabaseException(MiniChatDbError.ENTITY_NOT_FOUND, "ChatMessage with id " + id + " not found!");
        }
        return chatMessage;
    }

    public List<ChatMessageEntity> findLastChatMessages() {
        final TypedQuery<ChatMessageEntity> query
                = entityManager.createNamedQuery(ChatMessageEntity.FIND_LAST_MESSAGES, ChatMessageEntity.class);
        query.setMaxResults(10);
        return query.getResultList();
    }

    public List<ChatMessageEntity> findChatMessagesSince(final Date timestamp) {
        final TypedQuery<ChatMessageEntity> query
                = entityManager.createNamedQuery(ChatMessageEntity.FIND_MESSAGES_SINCE, ChatMessageEntity.class);
        query.setParameter("since", timestamp);
        query.setMaxResults(10);
        return query.getResultList();
    }
}
