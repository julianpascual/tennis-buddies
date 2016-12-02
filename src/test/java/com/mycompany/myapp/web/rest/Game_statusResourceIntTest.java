package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TennisBuddiesApp;

import com.mycompany.myapp.domain.Game_status;
import com.mycompany.myapp.repository.Game_statusRepository;
import com.mycompany.myapp.service.Game_statusService;
import com.mycompany.myapp.service.dto.Game_statusDTO;
import com.mycompany.myapp.service.mapper.Game_statusMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Game_statusResource REST controller.
 *
 * @see Game_statusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TennisBuddiesApp.class)
public class Game_statusResourceIntTest {

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Inject
    private Game_statusRepository game_statusRepository;

    @Inject
    private Game_statusMapper game_statusMapper;

    @Inject
    private Game_statusService game_statusService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restGame_statusMockMvc;

    private Game_status game_status;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Game_statusResource game_statusResource = new Game_statusResource();
        ReflectionTestUtils.setField(game_statusResource, "game_statusService", game_statusService);
        this.restGame_statusMockMvc = MockMvcBuilders.standaloneSetup(game_statusResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Game_status createEntity(EntityManager em) {
        Game_status game_status = new Game_status()
                .status(DEFAULT_STATUS);
        return game_status;
    }

    @Before
    public void initTest() {
        game_status = createEntity(em);
    }

    @Test
    @Transactional
    public void createGame_status() throws Exception {
        int databaseSizeBeforeCreate = game_statusRepository.findAll().size();

        // Create the Game_status
        Game_statusDTO game_statusDTO = game_statusMapper.game_statusToGame_statusDTO(game_status);

        restGame_statusMockMvc.perform(post("/api/game-statuses")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(game_statusDTO)))
                .andExpect(status().isCreated());

        // Validate the Game_status in the database
        List<Game_status> game_statuses = game_statusRepository.findAll();
        assertThat(game_statuses).hasSize(databaseSizeBeforeCreate + 1);
        Game_status testGame_status = game_statuses.get(game_statuses.size() - 1);
        assertThat(testGame_status.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void getAllGame_statuses() throws Exception {
        // Initialize the database
        game_statusRepository.saveAndFlush(game_status);

        // Get all the game_statuses
        restGame_statusMockMvc.perform(get("/api/game-statuses?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(game_status.getId().intValue())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getGame_status() throws Exception {
        // Initialize the database
        game_statusRepository.saveAndFlush(game_status);

        // Get the game_status
        restGame_statusMockMvc.perform(get("/api/game-statuses/{id}", game_status.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(game_status.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGame_status() throws Exception {
        // Get the game_status
        restGame_statusMockMvc.perform(get("/api/game-statuses/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGame_status() throws Exception {
        // Initialize the database
        game_statusRepository.saveAndFlush(game_status);
        int databaseSizeBeforeUpdate = game_statusRepository.findAll().size();

        // Update the game_status
        Game_status updatedGame_status = game_statusRepository.findOne(game_status.getId());
        updatedGame_status
                .status(UPDATED_STATUS);
        Game_statusDTO game_statusDTO = game_statusMapper.game_statusToGame_statusDTO(updatedGame_status);

        restGame_statusMockMvc.perform(put("/api/game-statuses")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(game_statusDTO)))
                .andExpect(status().isOk());

        // Validate the Game_status in the database
        List<Game_status> game_statuses = game_statusRepository.findAll();
        assertThat(game_statuses).hasSize(databaseSizeBeforeUpdate);
        Game_status testGame_status = game_statuses.get(game_statuses.size() - 1);
        assertThat(testGame_status.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void deleteGame_status() throws Exception {
        // Initialize the database
        game_statusRepository.saveAndFlush(game_status);
        int databaseSizeBeforeDelete = game_statusRepository.findAll().size();

        // Get the game_status
        restGame_statusMockMvc.perform(delete("/api/game-statuses/{id}", game_status.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Game_status> game_statuses = game_statusRepository.findAll();
        assertThat(game_statuses).hasSize(databaseSizeBeforeDelete - 1);
    }
}
