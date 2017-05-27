package com.balance.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by KEVIN on 26/05/2017.
 */
@Entity
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="model_id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="model", cascade = CascadeType.ALL)
    private Set<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
