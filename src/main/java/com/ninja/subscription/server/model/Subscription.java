package com.ninja.subscription.server.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "subscription")
@Proxy(lazy = false)
public class Subscription {


    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private long id;

    @Column(name = "userid", nullable = false)
    private String userid;

    @Column(name = "saledate", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDateTime saleDate;

    @Column(name = "finishdate", nullable = false)

    private LocalDateTime finishDate;

    @Column(name = "price", nullable = false)
    private long price;

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
    private User associatedUser;

    /*********************
     * Getters and Setters
     *
     ********************/
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

    public User getAssociatedUser() {
        return associatedUser;
    }

    public void setAssociatedUser(User associatedUser) {
        this.associatedUser = associatedUser;
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

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userid) {
        this.userid = userid;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
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

