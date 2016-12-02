package com.mycompany.myapp.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Review.
 */
@Entity
@Table(name = "review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "review")
    private Integer review;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    private User id_user_from;

    @ManyToOne
    private User id_user_to;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReview() {
        return review;
    }

    public Review review(Integer review) {
        this.review = review;
        return this;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public String getComment() {
        return comment;
    }

    public Review comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getId_user_from() {
        return id_user_from;
    }

    public Review id_user_from(User user) {
        this.id_user_from = user;
        return this;
    }

    public void setId_user_from(User user) {
        this.id_user_from = user;
    }

    public User getId_user_to() {
        return id_user_to;
    }

    public Review id_user_to(User user) {
        this.id_user_to = user;
        return this;
    }

    public void setId_user_to(User user) {
        this.id_user_to = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Review review = (Review) o;
        if(review.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Review{" +
            "id=" + id +
            ", review='" + review + "'" +
            ", comment='" + comment + "'" +
            '}';
    }
}
