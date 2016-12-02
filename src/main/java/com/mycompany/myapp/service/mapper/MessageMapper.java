package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.MessageDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Message and its DTO MessageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MessageMapper {

    MessageDTO messageToMessageDTO(Message message);

    List<MessageDTO> messagesToMessageDTOs(List<Message> messages);

    @Mapping(target = "id_user_from", ignore = true)
    @Mapping(target = "id_user_to", ignore = true)
    Message messageDTOToMessage(MessageDTO messageDTO);

    List<Message> messageDTOsToMessages(List<MessageDTO> messageDTOs);
}
