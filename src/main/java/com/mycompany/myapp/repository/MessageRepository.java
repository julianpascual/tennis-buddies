package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Message;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Message entity.
 */
@SuppressWarnings("unused")
public interface MessageRepository extends JpaRepository<Message,Long> {

    @Query("select message from Message message where message.id_user_from.login = ?#{principal.username}")
    List<Message> findById_user_fromIsCurrentUser();

    @Query("select message from Message message where message.id_user_to.login = ?#{principal.username}")
    List<Message> findById_user_toIsCurrentUser();

}
