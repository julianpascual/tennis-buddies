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
    private User id_user_requestor;

    @ManyToOne
    private User id_user_requested;

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

    public User getId_user_requestor() {
        return id_user_requestor;
    }

    public Game id_user_requestor(User user) {
        this.id_user_requestor = user;
        return this;
    }

    public void setId_user_requestor(User user) {
        this.id_user_requestor = user;
    }

    public User getId_user_requested() {
        return id_user_requested;
    }

    public Game id_user_requested(User user) {
        this.id_user_requested = user;
        return this;
    }

    public void setId_user_requested(User user) {
        this.id_user_requested = user;
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
