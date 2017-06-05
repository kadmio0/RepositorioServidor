package com.balance.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by da_20 on 29/5/2017.
 */
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String token;

    private Boolean active=true;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="limit_date")
    @Temporal(TemporalType.DATE)
    private Date limit_date;

    public Date getLimit_date() {
        return limit_date;
    }

    public void setLimit_date(Date limit_date) {
        this.limit_date = limit_date;
    }

    public Integer getId() {
        return id;
    }

    public Token(String token) {
        this.token = token;
    }

    public Token(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public Token() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}