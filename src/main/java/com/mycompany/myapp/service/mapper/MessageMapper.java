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

    @Mapping(source = "idUserFrom.id", target = "idUserFromId")
    @Mapping(source = "idUserFrom.login", target = "idUserFromLogin")
    @Mapping(source = "idUserTo.id", target = "idUserToId")
    @Mapping(source = "idUserTo.login", target = "idUserToLogin")
    MessageDTO messageToMessageDTO(Message message);

    List<MessageDTO> messagesToMessageDTOs(List<Message> messages);

    @Mapping(source = "idUserFromId", target = "idUserFrom")
    @Mapping(source = "idUserToId", target = "idUserTo")
    Message messageDTOToMessage(MessageDTO messageDTO);

    List<Message> messageDTOsToMessages(List<MessageDTO> messageDTOs);
}
