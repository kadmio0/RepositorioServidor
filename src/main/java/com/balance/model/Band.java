package com.balance.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by KEVIN on 26/05/2017.
 */
@Entity
@Table(name = "band")
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="band_id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="band", cascade = CascadeType.ALL)
    private Set<Terminal> terminals;

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

    public Set<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(Set<Terminal> terminals) {
        this.terminals = terminals;
    }
}
