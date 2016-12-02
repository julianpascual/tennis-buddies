package com.mycompany.myapp.service.dto;

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


    private Long id_user_fromId;
    
    private Long id_user_toId;
    
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

    public Long getId_user_fromId() {
        return id_user_fromId;
    }

    public void setId_user_fromId(Long userId) {
        this.id_user_fromId = userId;
    }

    public Long getId_user_toId() {
        return id_user_toId;
    }

    public void setId_user_toId(Long userId) {
        this.id_user_toId = userId;
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
            '}';
    }
}
