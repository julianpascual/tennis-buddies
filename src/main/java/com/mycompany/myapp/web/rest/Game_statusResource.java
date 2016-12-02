package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.Game_statusService;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.Game_statusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Game_status.
 */
@RestController
@RequestMapping("/api")
public class Game_statusResource {

    private final Logger log = LoggerFactory.getLogger(Game_statusResource.class);
        
    @Inject
    private Game_statusService game_statusService;

    /**
     * POST  /game-statuses : Create a new game_status.
     *
     * @param game_statusDTO the game_statusDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new game_statusDTO, or with status 400 (Bad Request) if the game_status has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/game-statuses")
    @Timed
    public ResponseEntity<Game_statusDTO> createGame_status(@RequestBody Game_statusDTO game_statusDTO) throws URISyntaxException {
        log.debug("REST request to save Game_status : {}", game_statusDTO);
        if (game_statusDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("game_status", "idexists", "A new game_status cannot already have an ID")).body(null);
        }
        Game_statusDTO result = game_statusService.save(game_statusDTO);
        return ResponseEntity.created(new URI("/api/game-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("game_status", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /game-statuses : Updates an existing game_status.
     *
     * @param game_statusDTO the game_statusDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated game_statusDTO,
     * or with status 400 (Bad Request) if the game_statusDTO is not valid,
     * or with status 500 (Internal Server Error) if the game_statusDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/game-statuses")
    @Timed
    public ResponseEntity<Game_statusDTO> updateGame_status(@RequestBody Game_statusDTO game_statusDTO) throws URISyntaxException {
        log.debug("REST request to update Game_status : {}", game_statusDTO);
        if (game_statusDTO.getId() == null) {
            return createGame_status(game_statusDTO);
        }
        Game_statusDTO result = game_statusService.save(game_statusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("game_status", game_statusDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /game-statuses : get all the game_statuses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of game_statuses in body
     */
    @GetMapping("/game-statuses")
    @Timed
    public List<Game_statusDTO> getAllGame_statuses() {
        log.debug("REST request to get all Game_statuses");
        return game_statusService.findAll();
    }

    /**
     * GET  /game-statuses/:id : get the "id" game_status.
     *
     * @param id the id of the game_statusDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the game_statusDTO, or with status 404 (Not Found)
     */
    @GetMapping("/game-statuses/{id}")
    @Timed
    public ResponseEntity<Game_statusDTO> getGame_status(@PathVariable Long id) {
        log.debug("REST request to get Game_status : {}", id);
        Game_statusDTO game_statusDTO = game_statusService.findOne(id);
        return Optional.ofNullable(game_statusDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /game-statuses/:id : delete the "id" game_status.
     *
     * @param id the id of the game_statusDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/game-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteGame_status(@PathVariable Long id) {
        log.debug("REST request to delete Game_status : {}", id);
        game_statusService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("game_status", id.toString())).build();
    }

}
