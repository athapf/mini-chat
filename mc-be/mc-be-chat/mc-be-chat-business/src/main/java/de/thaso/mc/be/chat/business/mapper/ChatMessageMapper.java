package de.thaso.mc.be.chat.business.mapper;

import de.thaso.mc.be.chat.service.ChatMessage;
import de.thaso.minichat.db.store.ChatMessageEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * ChatServiceMapper
 *
 * @author thaler
 * @since 22.09.16
 */
@Mapper
public interface ChatMessageMapper {

    ChatMessage chatMessageToDO(ChatMessageEntity chatMessageEntity);

    ChatMessageEntity chatMessageToEntity(ChatMessage chatMessage);

    List<ChatMessage> chatMessageListToDOList(List<ChatMessageEntity> chatMessageEntityList);
}
