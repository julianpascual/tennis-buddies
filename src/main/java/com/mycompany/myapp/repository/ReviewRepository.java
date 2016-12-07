package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Review;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Review entity.
 */
@SuppressWarnings("unused")
public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("select review from Review review where review.idUserFrom.login = ?#{principal.username}")
    List<Review> findByIdUserFromIsCurrentUser();

    @Query("select review from Review review where review.idUserTo.login = ?#{principal.username}")
    List<Review> findByIdUserToIsCurrentUser();

    List<Review> findByIdUserTo_Login(String login);
}
