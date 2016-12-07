package com.mycompany.myapp.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Game.
 */
@Entity
@Table(name = "game")
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "result")
    private String result;

    @ManyToOne
    private Game_status status;

    @ManyToOne
    private User idUserRequestor;

    @ManyToOne
    private User idUserRequested;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public Game date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public Game result(String result) {
        this.result = result;
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Game_status getStatus() {
        return status;
    }

    public Game status(Game_status game_status) {
        this.status = game_status;
        return this;
    }

    public void setStatus(Game_status game_status) {
        this.status = game_status;
    }

    public User getIdUserRequestor() {
        return idUserRequestor;
    }

    public Game idUserRequestor(User user) {
        this.idUserRequestor = user;
        return this;
    }

    public void setIdUserRequestor(User user) {
        this.idUserRequestor = user;
    }

    public User getIdUserRequested() {
        return idUserRequested;
    }

    public Game idUserRequested(User user) {
        this.idUserRequested = user;
        return this;
    }

    public void setIdUserRequested(User user) {
        this.idUserRequested = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Game game = (Game) o;
        if(game.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Game{" +
            "id=" + id +
            ", date='" + date + "'" +
            ", result='" + result + "'" +
            '}';
    }
}
