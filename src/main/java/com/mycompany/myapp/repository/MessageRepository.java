package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Message;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Message entity.
 */
@SuppressWarnings("unused")
public interface MessageRepository extends JpaRepository<Message,Long> {

}
