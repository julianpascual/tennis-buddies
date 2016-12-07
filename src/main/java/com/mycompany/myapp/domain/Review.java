package com.mycompany.myapp.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
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

    @Column(name = "date")
    private ZonedDateTime date;

    @ManyToOne
    private User idUserFrom;

    @ManyToOne
    private User idUserTo;

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

    public ZonedDateTime getDate() {
        return date;
    }

    public Review date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public User getIdUserFrom() {
        return idUserFrom;
    }

    public Review idUserFrom(User user) {
        this.idUserFrom = user;
        return this;
    }

    public void setIdUserFrom(User user) {
        this.idUserFrom = user;
    }

    public User getIdUserTo() {
        return idUserTo;
    }

    public Review idUserTo(User user) {
        this.idUserTo = user;
        return this;
    }

    public void setIdUserTo(User user) {
        this.idUserTo = user;
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
            ", date='" + date + "'" +
            '}';
    }
}
