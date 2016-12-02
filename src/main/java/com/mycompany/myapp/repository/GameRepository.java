package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Game;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Game entity.
 */
@SuppressWarnings("unused")
public interface GameRepository extends JpaRepository<Game,Long> {

    @Query("select game from Game game where game.id_user_requestor.login = ?#{principal.username}")
    List<Game> findById_user_requestorIsCurrentUser();

    @Query("select game from Game game where game.id_user_requested.login = ?#{principal.username}")
    List<Game> findById_user_requestedIsCurrentUser();

}
