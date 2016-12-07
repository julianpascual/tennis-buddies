package com.mycompany.myapp.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Review entity.
 */
public class ReviewDTO implements Serializable {

    private Long id;

    private Integer review;

    private String comment;

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
    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

        ReviewDTO reviewDTO = (ReviewDTO) o;

        if ( ! Objects.equals(id, reviewDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
            "id=" + id +
            ", review='" + review + "'" +
            ", comment='" + comment + "'" +
            ", date='" + date + "'" +
            '}';
    }
}
