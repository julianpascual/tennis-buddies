package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Review;
import com.mycompany.myapp.repository.ReviewRepository;
import com.mycompany.myapp.service.dto.ReviewDTO;
import com.mycompany.myapp.service.mapper.ReviewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Review.
 */
@Service
@Transactional
public class ReviewService {

    private final Logger log = LoggerFactory.getLogger(ReviewService.class);

    @Inject
    private ReviewRepository reviewRepository;

    @Inject
    private ReviewMapper reviewMapper;

    /**
     * Save a review.
     *
     * @param reviewDTO the entity to save
     * @return the persisted entity
     */
    public ReviewDTO save(ReviewDTO reviewDTO) {
        log.debug("Request to save Review : {}", reviewDTO);
        Review review = reviewMapper.reviewDTOToReview(reviewDTO);
        review = reviewRepository.save(review);
        ReviewDTO result = reviewMapper.reviewToReviewDTO(review);
        return result;
    }

    /**
     *  Get all the reviews.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ReviewDTO> findAll() {
        log.debug("Request to get all Reviews");
        List<ReviewDTO> result = reviewRepository.findAll().stream()
            .map(reviewMapper::reviewToReviewDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get all the reviews by user id.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ReviewDTO> findAllByUserId(String userId) {
        log.debug("Request to get all Reviews for User with Id: ", userId);
        List<ReviewDTO> result = reviewRepository.findBy_Id_User(userId).stream()
            .map(reviewMapper::reviewToReviewDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one review by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public ReviewDTO findOne(Long id) {
        log.debug("Request to get Review : {}", id);
        Review review = reviewRepository.findOne(id);
        ReviewDTO reviewDTO = reviewMapper.reviewToReviewDTO(review);
        return reviewDTO;
    }

    /**
     *  Delete the  review by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Review : {}", id);
        reviewRepository.delete(id);
    }
}
