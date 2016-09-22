package de.thaso.minichat.db.store;

import de.thaso.minichat.db.common.MiniChatDatabaseException;
import de.thaso.minichat.db.common.MiniChatDbError;
import de.thaso.minichat.db.store.utils.MiniChatDatabaseExceptionCodeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * ChatMessageDAOTest
 *
 * @author thaler
 * @since 13.09.16
 */
public class ChatMessageDAOTest {

    public static final MiniChatDatabaseExceptionCodeMatcher EXCEPTION_MATCHER_ENTITY_NOT_FOUND
            = new MiniChatDatabaseExceptionCodeMatcher(MiniChatDbError.ENTITY_NOT_FOUND);

    @InjectMocks
    private ChatMessageDAO underTest;

    @Mock
    private EntityManager entityManager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long primaryKey;
    private ChatMessageEntity chatMessage;

    @Before
    public void setUp() {
        initMocks(this);

        primaryKey = 1L;
        chatMessage = new ChatMessageEntity();
        when(entityManager.find(ChatMessageEntity.class, primaryKey)).thenReturn(chatMessage);
    }

    @Test
    public void storeChatMessage() {
        // when
        final ChatMessageEntity result = underTest.storeChatMessage(chatMessage);
        // then
        verify(entityManager).persist(chatMessage);
        assertThat(result, is(chatMessage));
    }

    @Test
    public void findChatMessage() {
        // when
        final ChatMessageEntity result = underTest.findChatMessageById(primaryKey);
        // then
        assertThat(result, is(chatMessage));
    }

    @Test
    public void findChatMessage_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(ChatMessageEntity.class, primaryKey)).thenReturn(null);
        // when
        final ChatMessageEntity result = underTest.findChatMessageById(primaryKey);
        // then
        assertThat(result, is(nullValue()));
    }

    @Test
    public void loadChatMessage() {
        // when
        final ChatMessageEntity result = underTest.loadChatMessageById(primaryKey);
        // then
        assertThat(result, is(chatMessage));
    }

    @Test
    public void loadChatMessage_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(ChatMessageEntity.class, primaryKey)).thenReturn(null);
        exception.expect(MiniChatDatabaseException.class);
        exception.expectMessage(containsString(" " + primaryKey.toString() + " "));
        exception.expect(EXCEPTION_MATCHER_ENTITY_NOT_FOUND);
        // when
        final ChatMessageEntity result = underTest.loadChatMessageById(primaryKey);
    }

    @Test
    public void findLastChatMessages() {
        // given
        final TypedQuery query = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(ChatMessageEntity.FIND_LAST_MESSAGES,ChatMessageEntity.class)).thenReturn(query);
        final List<ChatMessageEntity> messageEntityList = new ArrayList<>();
        when(query.getResultList()).thenReturn(messageEntityList);
        // when
        final List<ChatMessageEntity> result = underTest.findLastChatMessages();
        // then
        assertThat(result,is(messageEntityList));
        verify(query).setMaxResults(10);
    }
}