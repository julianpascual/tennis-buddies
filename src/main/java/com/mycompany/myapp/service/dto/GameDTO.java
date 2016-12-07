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

    private Long idUserRequestorId;
    

    private String idUserRequestorLogin;

    private Long idUserRequestedId;
    

    private String idUserRequestedLogin;

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

    public Long getIdUserRequestorId() {
        return idUserRequestorId;
    }

    public void setIdUserRequestorId(Long userId) {
        this.idUserRequestorId = userId;
    }


    public String getIdUserRequestorLogin() {
        return idUserRequestorLogin;
    }

    public void setIdUserRequestorLogin(String userLogin) {
        this.idUserRequestorLogin = userLogin;
    }

    public Long getIdUserRequestedId() {
        return idUserRequestedId;
    }

    public void setIdUserRequestedId(Long userId) {
        this.idUserRequestedId = userId;
    }


    public String getIdUserRequestedLogin() {
        return idUserRequestedLogin;
    }

    public void setIdUserRequestedLogin(String userLogin) {
        this.idUserRequestedLogin = userLogin;
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
