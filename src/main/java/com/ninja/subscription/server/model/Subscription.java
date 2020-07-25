package com.ninja.subscription.server.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "subscription")
@Proxy(lazy = false)
public class Subscription {


    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private long id;

    @Column(name = "saledate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date saleDate;

    @Column(name = "finishdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date finishDate;


    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "current")
    private Boolean current;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "associatedSub",orphanRemoval=true)
    private Set<VisitDate> visitDates = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor associatedInstructor;

    @ManyToOne
    @JoinColumn(name="user_id")
    private FirebaseUsers associatedFirebaseUsers;

    @Column(name = "count", nullable = false)
    private int count;

    @ManyToOne
    @JoinColumn(name="price_id")
    private Price associatedPrice;


    /*********************
     * Getters and Setters
     *
     ********************/
    public Price getAssociatedPrice() {
        return associatedPrice;
    }

    public void setAssociatedPrice(Price associatedPrice) {
        this.associatedPrice = associatedPrice;
    }


    public Instructor getAssociatedInstructor() {
        return associatedInstructor;
    }

    public void setAssociatedInstructor(Instructor associatedInstructor) {
        this.associatedInstructor = associatedInstructor;
    }

    public FirebaseUsers getAssociatedFirebaseUsers() {
        return associatedFirebaseUsers;
    }

    public void setAssociatedFirebaseUsers(FirebaseUsers associatedFirebaseUsers) {
        this.associatedFirebaseUsers = associatedFirebaseUsers;
    }
    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }

    public void setVisitDates(Set<VisitDate> visitDates) {
        this.visitDates = visitDates;
    }

    public Set<VisitDate> getVisitDates() {
        return visitDates;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}

