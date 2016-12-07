package com.mycompany.myapp.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Message.
 */
@Entity
@Table(name = "message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "message")
    private String message;

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

    public String getMessage() {
        return message;
    }

    public Message message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public Message date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public User getIdUserFrom() {
        return idUserFrom;
    }

    public Message idUserFrom(User user) {
        this.idUserFrom = user;
        return this;
    }

    public void setIdUserFrom(User user) {
        this.idUserFrom = user;
    }

    public User getIdUserTo() {
        return idUserTo;
    }

    public Message idUserTo(User user) {
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
        Message message = (Message) o;
        if(message.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Message{" +
            "id=" + id +
            ", message='" + message + "'" +
            ", date='" + date + "'" +
            '}';
    }
}
