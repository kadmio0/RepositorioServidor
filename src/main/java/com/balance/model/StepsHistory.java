package com.balance.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by da_20 on 5/6/2017.
 */
@Entity
public class StepsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="steps_history_id")
    private Long id;

    @Column(name="steps")
    private Integer steps;

    @Column(name="distance")
    private long distance;

    @Column(name="user")
    private Integer user;

    @Column(name="date")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public StepsHistory(Integer steps, Long distance, Integer user,Date date){
        this.steps=steps;
        this.distance=distance;
        this.user=user;
        this.date=date;
    }

    public StepsHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
