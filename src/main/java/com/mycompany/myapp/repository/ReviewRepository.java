package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Review;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Review entity.
 */
@SuppressWarnings("unused")
public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("select review from Review review where review.id_user_from.login = ?#{principal.username}")
    List<Review> findById_user_fromIsCurrentUser();

    @Query("select review from Review review where review.id_user_to.login = ?#{principal.username}")
    List<Review> findById_user_toIsCurrentUser();

}
