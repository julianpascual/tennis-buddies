package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PlayerService;
import com.mycompany.myapp.domain.Player;
import com.mycompany.myapp.repository.PlayerRepository;
import com.mycompany.myapp.service.dto.PlayerDTO;
import com.mycompany.myapp.service.mapper.PlayerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Player.
 */
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService{

    private final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);
    
    @Inject
    private PlayerRepository playerRepository;

    @Inject
    private PlayerMapper playerMapper;

    /**
     * Save a player.
     *
     * @param playerDTO the entity to save
     * @return the persisted entity
     */
    public PlayerDTO save(PlayerDTO playerDTO) {
        log.debug("Request to save Player : {}", playerDTO);
        Player player = playerMapper.playerDTOToPlayer(playerDTO);
        player = playerRepository.save(player);
        PlayerDTO result = playerMapper.playerToPlayerDTO(player);
        return result;
    }

    /**
     *  Get all the players.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<PlayerDTO> findAll() {
        log.debug("Request to get all Players");
        List<PlayerDTO> result = playerRepository.findAll().stream()
            .map(playerMapper::playerToPlayerDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one player by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public PlayerDTO findOne(Long id) {
        log.debug("Request to get Player : {}", id);
        Player player = playerRepository.findOne(id);
        PlayerDTO playerDTO = playerMapper.playerToPlayerDTO(player);
        return playerDTO;
    }

    /**
     *  Delete the  player by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Player : {}", id);
        playerRepository.delete(id);
    }
}
