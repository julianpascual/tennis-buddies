package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ReviewDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Review and its DTO ReviewDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, UserMapper.class, })
public interface ReviewMapper {

    @Mapping(source = "idUserFrom.id", target = "idUserFromId")
    @Mapping(source = "idUserFrom.login", target = "idUserFromLogin")
    @Mapping(source = "idUserTo.id", target = "idUserToId")
    @Mapping(source = "idUserTo.login", target = "idUserToLogin")
    ReviewDTO reviewToReviewDTO(Review review);

    List<ReviewDTO> reviewsToReviewDTOs(List<Review> reviews);

    @Mapping(source = "idUserFromId", target = "idUserFrom")
    @Mapping(source = "idUserToId", target = "idUserTo")
    Review reviewDTOToReview(ReviewDTO reviewDTO);

    List<Review> reviewDTOsToReviews(List<ReviewDTO> reviewDTOs);
}
