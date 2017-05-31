package com.balance.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by KEVIN on 26/05/2017.
 */
@Entity
@Table(name = "terminal")
public class Terminal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="terminal_id")
    private int id;

    @Column(name="serial")
    private String serial;

    @Column(name="active")
    private boolean active=false;

    @OneToOne(mappedBy = "terminal")
    private User user;

    @ManyToOne
    @JoinColumn(name="band_id")
    private Band band;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }
}
