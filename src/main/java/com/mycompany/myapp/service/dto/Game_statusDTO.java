package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Game_status entity.
 */
public class Game_statusDTO implements Serializable {

    private Long id;

    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Game_statusDTO game_statusDTO = (Game_statusDTO) o;

        if ( ! Objects.equals(id, game_statusDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Game_statusDTO{" +
            "id=" + id +
            ", status='" + status + "'" +
            '}';
    }
}
