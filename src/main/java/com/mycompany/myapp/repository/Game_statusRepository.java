package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Game_status;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Game_status entity.
 */
@SuppressWarnings("unused")
public interface Game_statusRepository extends JpaRepository<Game_status,Long> {

}
