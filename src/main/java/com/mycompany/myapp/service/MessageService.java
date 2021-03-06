package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Message;
import com.mycompany.myapp.repository.MessageRepository;
import com.mycompany.myapp.service.dto.MessageDTO;
import com.mycompany.myapp.service.mapper.MessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Message.
 */
@Service
@Transactional
public class MessageService {

    private final Logger log = LoggerFactory.getLogger(MessageService.class);

    @Inject
    private MessageRepository messageRepository;

    @Inject
    private MessageMapper messageMapper;

    /**
     * Save a message.
     *
     * @param messageDTO the entity to save
     * @return the persisted entity
     */
    public MessageDTO save(MessageDTO messageDTO) {
        log.debug("Request to save Message : {}", messageDTO);
        Message message = messageMapper.messageDTOToMessage(messageDTO);
        message = messageRepository.save(message);
        MessageDTO result = messageMapper.messageToMessageDTO(message);
        return result;
    }

    /**
     *  Get all the messages.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<MessageDTO> findAll() {
        log.debug("Request to get all Messages");
        List<MessageDTO> result = messageRepository.findAll().stream()
            .map(messageMapper::messageToMessageDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get all the conversation between users.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<MessageDTO> findConversation(String idUserFrom, String idUserTo) {
        log.debug("Request to get all Messages");
        List<MessageDTO> result = messageRepository.findConversation(idUserTo, idUserFrom).stream()
            .map(messageMapper::messageToMessageDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one message by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public MessageDTO findOne(Long id) {
        log.debug("Request to get Message : {}", id);
        Message message = messageRepository.findOne(id);
        MessageDTO messageDTO = messageMapper.messageToMessageDTO(message);
        return messageDTO;
    }

    /**
     *  Delete the  message by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Message : {}", id);
        messageRepository.delete(id);
    }
}
