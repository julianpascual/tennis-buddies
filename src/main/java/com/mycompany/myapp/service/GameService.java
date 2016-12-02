package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Game;
import com.mycompany.myapp.repository.GameRepository;
import com.mycompany.myapp.service.dto.GameDTO;
import com.mycompany.myapp.service.mapper.GameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Game.
 */
@Service
@Transactional
public class GameService {

    private final Logger log = LoggerFactory.getLogger(GameService.class);
    
    @Inject
    private GameRepository gameRepository;

    @Inject
    private GameMapper gameMapper;

    /**
     * Save a game.
     *
     * @param gameDTO the entity to save
     * @return the persisted entity
     */
    public GameDTO save(GameDTO gameDTO) {
        log.debug("Request to save Game : {}", gameDTO);
        Game game = gameMapper.gameDTOToGame(gameDTO);
        game = gameRepository.save(game);
        GameDTO result = gameMapper.gameToGameDTO(game);
        return result;
    }

    /**
     *  Get all the games.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<GameDTO> findAll() {
        log.debug("Request to get all Games");
        List<GameDTO> result = gameRepository.findAll().stream()
            .map(gameMapper::gameToGameDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one game by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public GameDTO findOne(Long id) {
        log.debug("Request to get Game : {}", id);
        Game game = gameRepository.findOne(id);
        GameDTO gameDTO = gameMapper.gameToGameDTO(game);
        return gameDTO;
    }

    /**
     *  Delete the  game by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Game : {}", id);
        gameRepository.delete(id);
    }
}
