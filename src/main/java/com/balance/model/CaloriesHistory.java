package com.balance.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by KEVIN on 05/06/2017.
 */
@Entity
@Table(name = "calories_history")
public class CaloriesHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="calories_history_id")
    private Long id;

    @Column(name="calories")
    private Integer calories;

    @Column(name="user_id")
    private  Integer user;

    @Column(name="date")
    private Date date;

    public CaloriesHistory(Integer calories, Integer user, Date date) {
        this.calories = calories;
        this.user = user;
        this.date = date;
    }

    public CaloriesHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
