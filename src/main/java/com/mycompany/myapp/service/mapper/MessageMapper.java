package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.MessageDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Message and its DTO MessageDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, UserMapper.class, })
public interface MessageMapper {

    @Mapping(source = "id_user_from.id", target = "id_user_fromId")
    @Mapping(source = "id_user_from.login", target = "id_user_fromLogin")
    @Mapping(source = "id_user_to.id", target = "id_user_toId")
    @Mapping(source = "id_user_to.login", target = "id_user_toLogin")
    MessageDTO messageToMessageDTO(Message message);

    List<MessageDTO> messagesToMessageDTOs(List<Message> messages);

    @Mapping(source = "id_user_fromId", target = "id_user_from")
    @Mapping(source = "id_user_toId", target = "id_user_to")
    Message messageDTOToMessage(MessageDTO messageDTO);

    List<Message> messageDTOsToMessages(List<MessageDTO> messageDTOs);
}
