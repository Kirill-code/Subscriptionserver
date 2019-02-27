package com.ninja.subscription.server.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    public Subscription(String userid, Date saleDate, Date finishDate, long price, String description, List<VisitDate> visitDates, Instructor associatedInstructor) {
        this.userid = userid;
        this.saleDate = saleDate;
        this.finishDate = finishDate;
        this.price = price;
        this.description = description;
        this.visitDates = visitDates;
        this.associatedInstructor = associatedInstructor;
    }

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


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "associatedSub")
    private List<VisitDate> visitDates = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor associatedInstructor;


    public String getUserid() {
        return userid;
    }

    public Subscription() {
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
     * *******************/

    public void setVisitDates(List<VisitDate> visitDates) {
        this.visitDates = visitDates;
    }

    public List<VisitDate> getVisitDates() {
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

  /*  public long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
    }*/

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

