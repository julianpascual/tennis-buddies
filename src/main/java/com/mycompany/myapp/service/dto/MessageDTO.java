package com.mycompany.myapp.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Message entity.
 */
public class MessageDTO implements Serializable {

    private Long id;

    private String message;

    private ZonedDateTime date;


    private Long idUserFromId;
    

    private String idUserFromLogin;

    private Long idUserToId;
    

    private String idUserToLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Long getIdUserFromId() {
        return idUserFromId;
    }

    public void setIdUserFromId(Long userId) {
        this.idUserFromId = userId;
    }


    public String getIdUserFromLogin() {
        return idUserFromLogin;
    }

    public void setIdUserFromLogin(String userLogin) {
        this.idUserFromLogin = userLogin;
    }

    public Long getIdUserToId() {
        return idUserToId;
    }

    public void setIdUserToId(Long userId) {
        this.idUserToId = userId;
    }


    public String getIdUserToLogin() {
        return idUserToLogin;
    }

    public void setIdUserToLogin(String userLogin) {
        this.idUserToLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessageDTO messageDTO = (MessageDTO) o;

        if ( ! Objects.equals(id, messageDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
            "id=" + id +
            ", message='" + message + "'" +
            ", date='" + date + "'" +
            '}';
    }
}
