package com.mycompany.myapp.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Game entity.
 */
public class GameDTO implements Serializable {

    private Long id;

    private ZonedDateTime date;

    private String result;


    private Long statusId;
    

    private String statusStatus;

    private Long id_user_requestorId;
    

    private String id_user_requestorLogin;

    private Long id_user_requestedId;
    

    private String id_user_requestedLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long game_statusId) {
        this.statusId = game_statusId;
    }


    public String getStatusStatus() {
        return statusStatus;
    }

    public void setStatusStatus(String game_statusStatus) {
        this.statusStatus = game_statusStatus;
    }

    public Long getId_user_requestorId() {
        return id_user_requestorId;
    }

    public void setId_user_requestorId(Long userId) {
        this.id_user_requestorId = userId;
    }


    public String getId_user_requestorLogin() {
        return id_user_requestorLogin;
    }

    public void setId_user_requestorLogin(String userLogin) {
        this.id_user_requestorLogin = userLogin;
    }

    public Long getId_user_requestedId() {
        return id_user_requestedId;
    }

    public void setId_user_requestedId(Long userId) {
        this.id_user_requestedId = userId;
    }


    public String getId_user_requestedLogin() {
        return id_user_requestedLogin;
    }

    public void setId_user_requestedLogin(String userLogin) {
        this.id_user_requestedLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GameDTO gameDTO = (GameDTO) o;

        if ( ! Objects.equals(id, gameDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "GameDTO{" +
            "id=" + id +
            ", date='" + date + "'" +
            ", result='" + result + "'" +
            '}';
    }
}
