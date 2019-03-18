package com.ninja.subscription.server.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "subscription")
@Proxy(lazy = false)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Subscription {


    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private long id;

    @Column(name = "userid", nullable = false)
    private String userid;

    @Column(name = "saledate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date saleDate;

    @Column(name = "finishdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date finishDate;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "description", nullable = false)
    private String description;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "associatedSub",orphanRemoval=true)
    private Set<VisitDate> visitDates = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor associatedInstructor;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Instructor getAssociatedInstructor() {
        return associatedInstructor;
    }

    public void setAssociatedInstructor(Instructor associatedInstructor) {
        this.associatedInstructor = associatedInstructor;
    }

    /*********************
     * Getters and Setters
     *
     * @param visitDates*******************/

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

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userid) {
        this.userid = userid;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

