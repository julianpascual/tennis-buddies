package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TennisBuddiesApp;

import com.mycompany.myapp.domain.Review;
import com.mycompany.myapp.repository.ReviewRepository;
import com.mycompany.myapp.service.ReviewService;
import com.mycompany.myapp.service.dto.ReviewDTO;
import com.mycompany.myapp.service.mapper.ReviewMapper;

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
 * Test class for the ReviewResource REST controller.
 *
 * @see ReviewResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TennisBuddiesApp.class)
public class ReviewResourceIntTest {

    private static final Integer DEFAULT_REVIEW = 1;
    private static final Integer UPDATED_REVIEW = 2;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    @Inject
    private ReviewRepository reviewRepository;

    @Inject
    private ReviewMapper reviewMapper;

    @Inject
    private ReviewService reviewService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restReviewMockMvc;

    private Review review;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReviewResource reviewResource = new ReviewResource();
        ReflectionTestUtils.setField(reviewResource, "reviewService", reviewService);
        this.restReviewMockMvc = MockMvcBuilders.standaloneSetup(reviewResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Review createEntity(EntityManager em) {
        Review review = new Review()
                .review(DEFAULT_REVIEW)
                .comment(DEFAULT_COMMENT);
        return review;
    }

    @Before
    public void initTest() {
        review = createEntity(em);
    }

    @Test
    @Transactional
    public void createReview() throws Exception {
        int databaseSizeBeforeCreate = reviewRepository.findAll().size();

        // Create the Review
        ReviewDTO reviewDTO = reviewMapper.reviewToReviewDTO(review);

        restReviewMockMvc.perform(post("/api/reviews")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reviewDTO)))
                .andExpect(status().isCreated());

        // Validate the Review in the database
        List<Review> reviews = reviewRepository.findAll();
        assertThat(reviews).hasSize(databaseSizeBeforeCreate + 1);
        Review testReview = reviews.get(reviews.size() - 1);
        assertThat(testReview.getReview()).isEqualTo(DEFAULT_REVIEW);
        assertThat(testReview.getComment()).isEqualTo(DEFAULT_COMMENT);
    }

    @Test
    @Transactional
    public void getAllReviews() throws Exception {
        // Initialize the database
        reviewRepository.saveAndFlush(review);

        // Get all the reviews
        restReviewMockMvc.perform(get("/api/reviews?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(review.getId().intValue())))
                .andExpect(jsonPath("$.[*].review").value(hasItem(DEFAULT_REVIEW)))
                .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())));
    }

    @Test
    @Transactional
    public void getReview() throws Exception {
        // Initialize the database
        reviewRepository.saveAndFlush(review);

        // Get the review
        restReviewMockMvc.perform(get("/api/reviews/{id}", review.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(review.getId().intValue()))
            .andExpect(jsonPath("$.review").value(DEFAULT_REVIEW))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReview() throws Exception {
        // Get the review
        restReviewMockMvc.perform(get("/api/reviews/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReview() throws Exception {
        // Initialize the database
        reviewRepository.saveAndFlush(review);
        int databaseSizeBeforeUpdate = reviewRepository.findAll().size();

        // Update the review
        Review updatedReview = reviewRepository.findOne(review.getId());
        updatedReview
                .review(UPDATED_REVIEW)
                .comment(UPDATED_COMMENT);
        ReviewDTO reviewDTO = reviewMapper.reviewToReviewDTO(updatedReview);

        restReviewMockMvc.perform(put("/api/reviews")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reviewDTO)))
                .andExpect(status().isOk());

        // Validate the Review in the database
        List<Review> reviews = reviewRepository.findAll();
        assertThat(reviews).hasSize(databaseSizeBeforeUpdate);
        Review testReview = reviews.get(reviews.size() - 1);
        assertThat(testReview.getReview()).isEqualTo(UPDATED_REVIEW);
        assertThat(testReview.getComment()).isEqualTo(UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void deleteReview() throws Exception {
        // Initialize the database
        reviewRepository.saveAndFlush(review);
        int databaseSizeBeforeDelete = reviewRepository.findAll().size();

        // Get the review
        restReviewMockMvc.perform(delete("/api/reviews/{id}", review.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Review> reviews = reviewRepository.findAll();
        assertThat(reviews).hasSize(databaseSizeBeforeDelete - 1);
    }
}
