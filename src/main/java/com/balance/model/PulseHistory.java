package com.balance.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PulseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pulseHistory_id")
    private Long id;
    private int bpm;
    private Date date;
    private Integer user;

    public PulseHistory(int bpm, Date date, Integer user) {
        this.bpm = bpm;
        this.date = date;
        this.user = user;
    }

    public PulseHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
