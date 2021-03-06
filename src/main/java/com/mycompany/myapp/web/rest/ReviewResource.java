package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.config.Constants;
import com.mycompany.myapp.service.ReviewService;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.ReviewDTO;
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
 * REST controller for managing Review.
 */
@RestController
@RequestMapping("/api")
public class ReviewResource {

    private final Logger log = LoggerFactory.getLogger(ReviewResource.class);

    @Inject
    private ReviewService reviewService;

    /**
     * POST  /reviews : Create a new review.
     *
     * @param reviewDTO the reviewDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reviewDTO, or with status 400 (Bad Request) if the review has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reviews")
    @Timed
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) throws URISyntaxException {
        log.debug("REST request to save Review : {}", reviewDTO);
        if (reviewDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("review", "idexists", "A new review cannot already have an ID")).body(null);
        }
        ReviewDTO result = reviewService.save(reviewDTO);
        return ResponseEntity.created(new URI("/api/reviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("review", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reviews : Updates an existing review.
     *
     * @param reviewDTO the reviewDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reviewDTO,
     * or with status 400 (Bad Request) if the reviewDTO is not valid,
     * or with status 500 (Internal Server Error) if the reviewDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reviews")
    @Timed
    public ResponseEntity<ReviewDTO> updateReview(@RequestBody ReviewDTO reviewDTO) throws URISyntaxException {
        log.debug("REST request to update Review : {}", reviewDTO);
        if (reviewDTO.getId() == null) {
            return createReview(reviewDTO);
        }
        ReviewDTO result = reviewService.save(reviewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("review", reviewDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reviews : get all the reviews.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of reviews in body
     */
    @GetMapping("/reviews")
    @Timed
    public List<ReviewDTO> getAllReviews() {
        log.debug("REST request to get all Reviews");
        return reviewService.findAll();
    }

    /**
     * GET  /reviews/user/:login : get all the reviews for a particular user.
     *
     * @param login the id of the reviewDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and the list of reviews in body
     */
    @GetMapping("/reviews/user/{login" + Constants.LOGIN_REGEX + "}")
    @Timed
    public List<ReviewDTO> getAllReviewsForUser(@PathVariable String login) {
        log.debug("REST request to get all Reviews for user: " + login);
        return reviewService.findAllForUser(login);
    }

    /**
     * GET  /reviews/:id : get the "id" review.
     *
     * @param id the id of the reviewDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reviewDTO, or with status 404 (Not Found)
     */
    @GetMapping("/reviews/{id}")
    @Timed
    public ResponseEntity<ReviewDTO> getReview(@PathVariable Long id) {
        log.debug("REST request to get Review : {}", id);
        ReviewDTO reviewDTO = reviewService.findOne(id);
        return Optional.ofNullable(reviewDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /reviews/:id : delete the "id" review.
     *
     * @param id the id of the reviewDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reviews/{id}")
    @Timed
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        log.debug("REST request to delete Review : {}", id);
        reviewService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("review", id.toString())).build();
    }

}
