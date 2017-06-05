package com.balance.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PulseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pulseHistory_id")
    private int id;
    int bpm;
    Date date;
    private Integer user;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
