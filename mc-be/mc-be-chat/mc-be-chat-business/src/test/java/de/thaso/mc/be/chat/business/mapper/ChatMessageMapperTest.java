package de.thaso.mc.be.chat.business.mapper;

import de.thaso.mc.be.chat.service.ChatMessage;
import de.thaso.minichat.db.store.ChatMessageEntity;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * ChatMessageMapperTest
 *
 * @author thaler
 * @since 22.09.16
 */
public class ChatMessageMapperTest {

    @InjectMocks
    private ChatMessageMapper underTest = Mappers.getMapper(ChatMessageMapper.class);

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void convertToEntity_WhenDOIsNull() {
        // when
        final ChatMessageEntity result = underTest.chatMessageToEntity(null);
        // then
        assertThat(result,is(nullValue()));
    }

    @Test
    public void convertListToDO() {
        // given
        final ArrayList<ChatMessageEntity> chatMessageEntityList = new ArrayList<>();
        final ChatMessageEntity firstMessageEntity = new ChatMessageEntity();
        firstMessageEntity.setId(743L);
        firstMessageEntity.setMessage("Hello");
        chatMessageEntityList.add(firstMessageEntity);
        final ChatMessageEntity secondMessageEntity = new ChatMessageEntity();
        secondMessageEntity.setId(234L);
        secondMessageEntity.setMessage("World");
        chatMessageEntityList.add(secondMessageEntity);
        // when
        final List<ChatMessage> chatMessageList = underTest.chatMessageListToDOList(chatMessageEntityList);
        // then
        assertThat(chatMessageList.size(),is(chatMessageEntityList.size()));

        ChatMessage firstChatMessage = chatMessageList.get(0);
        assertThat(firstChatMessage.getId(),is(firstMessageEntity.getId()));
        assertThat(firstChatMessage.getMessage(),is(firstMessageEntity.getMessage()));
        ChatMessage secondChatMessage = chatMessageList.get(1);
        assertThat(secondChatMessage.getId(),is(secondMessageEntity.getId()));
        assertThat(secondChatMessage.getMessage(),is(secondMessageEntity.getMessage()));
    }
}