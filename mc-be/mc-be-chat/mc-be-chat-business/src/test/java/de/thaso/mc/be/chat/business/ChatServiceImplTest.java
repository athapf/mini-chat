package de.thaso.mc.be.chat.business;

import de.thaso.mc.be.chat.business.mapper.ChatMessageMapper;
import de.thaso.mc.be.chat.service.ChatMessage;
import de.thaso.minichat.db.store.ChatMessageDAO;
import de.thaso.minichat.db.store.ChatMessageEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * ChatServiceImplTest
 *
 * @author thaler
 * @since 22.09.16
 */
public class ChatServiceImplTest {

    @InjectMocks
    private ChatServiceImpl underTest;

    @Mock
    private ChatMessageDAO chatMessageDAO;

    @Mock
    private ChatMessageMapper chatMessageMapper;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testName() {
        // given
        final ChatMessage chatMessage = new ChatMessage();
        final ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        when(chatMessageMapper.chatMessageToEntity(chatMessage)).thenReturn(chatMessageEntity);
        // when
        underTest.sendChatMessage(chatMessage);
        // then
        verify(chatMessageDAO).storeChatMessage(chatMessageEntity);
    }

    @Test
    public void testFindLast10ChatMessages() {
        // given
        final List<ChatMessageEntity> chatMessageEntityList = new ArrayList<>();
        when(chatMessageDAO.findLastChatMessages()).thenReturn(chatMessageEntityList);
        final List<ChatMessage> chatMessageList = new ArrayList<>();
        when(chatMessageMapper.chatMessageListToDOList(chatMessageEntityList)).thenReturn(chatMessageList);
        // when
        final List<ChatMessage> result = underTest.findLast10ChatMessages();
        // then
        assertThat(result, is(chatMessageList));
    }

    @Test
    public void testFind10ChatMessagesSince() {
        // given
        final Date timestamp = new Date();
        final List<ChatMessageEntity> chatMessageEntityList = new ArrayList<>();
        when(chatMessageDAO.findChatMessagesSince(timestamp)).thenReturn(chatMessageEntityList);
        final List<ChatMessage> chatMessageList = new ArrayList<>();
        when(chatMessageMapper.chatMessageListToDOList(chatMessageEntityList)).thenReturn(chatMessageList);
        // when
        final List<ChatMessage> result = underTest.find10ChatMessagesSince(timestamp);
        // then
        assertThat(result, is(chatMessageList));
    }
}