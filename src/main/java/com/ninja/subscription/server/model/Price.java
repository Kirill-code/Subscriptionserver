package com.ninja.subscription.server.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "price")

public class Price {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "price_id")
    private long id;

    @Column(name = "numberof_visits", nullable = false)
    private long numbers;

    @Column(name = "cost", nullable = false)
    private long cost;

    @Column(name = "duration", nullable = false)
    private int duration;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumbers() {
        return numbers;
    }

    public void setNumbers(long numbers) {
        this.numbers = numbers;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
