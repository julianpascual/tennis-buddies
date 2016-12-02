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

    @Mapping(source = "id_user_from.id", target = "id_user_fromId")
    @Mapping(source = "id_user_from.login", target = "id_user_fromLogin")
    @Mapping(source = "id_user_to.id", target = "id_user_toId")
    @Mapping(source = "id_user_to.login", target = "id_user_toLogin")
    ReviewDTO reviewToReviewDTO(Review review);

    List<ReviewDTO> reviewsToReviewDTOs(List<Review> reviews);

    @Mapping(source = "id_user_fromId", target = "id_user_from")
    @Mapping(source = "id_user_toId", target = "id_user_to")
    Review reviewDTOToReview(ReviewDTO reviewDTO);

    List<Review> reviewDTOsToReviews(List<ReviewDTO> reviewDTOs);
}
