package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Game_status;
import com.mycompany.myapp.repository.Game_statusRepository;
import com.mycompany.myapp.service.dto.Game_statusDTO;
import com.mycompany.myapp.service.mapper.Game_statusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Game_status.
 */
@Service
@Transactional
public class Game_statusService {

    private final Logger log = LoggerFactory.getLogger(Game_statusService.class);
    
    @Inject
    private Game_statusRepository game_statusRepository;

    @Inject
    private Game_statusMapper game_statusMapper;

    /**
     * Save a game_status.
     *
     * @param game_statusDTO the entity to save
     * @return the persisted entity
     */
    public Game_statusDTO save(Game_statusDTO game_statusDTO) {
        log.debug("Request to save Game_status : {}", game_statusDTO);
        Game_status game_status = game_statusMapper.game_statusDTOToGame_status(game_statusDTO);
        game_status = game_statusRepository.save(game_status);
        Game_statusDTO result = game_statusMapper.game_statusToGame_statusDTO(game_status);
        return result;
    }

    /**
     *  Get all the game_statuses.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Game_statusDTO> findAll() {
        log.debug("Request to get all Game_statuses");
        List<Game_statusDTO> result = game_statusRepository.findAll().stream()
            .map(game_statusMapper::game_statusToGame_statusDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one game_status by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Game_statusDTO findOne(Long id) {
        log.debug("Request to get Game_status : {}", id);
        Game_status game_status = game_statusRepository.findOne(id);
        Game_statusDTO game_statusDTO = game_statusMapper.game_statusToGame_statusDTO(game_status);
        return game_statusDTO;
    }

    /**
     *  Delete the  game_status by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Game_status : {}", id);
        game_statusRepository.delete(id);
    }
}
