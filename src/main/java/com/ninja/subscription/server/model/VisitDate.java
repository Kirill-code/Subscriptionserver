package com.ninja.subscription.server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="visitdate")

public class VisitDate {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public Subscription getAssociatedSub() {
        return associatedSub;
    }

    public void setAssociatedSub(Subscription associatedSub) {
        this.associatedSub = associatedSub;
    }

    @ManyToOne
    @JoinColumn(name="subscription_id")
    private Subscription associatedSub;

    public VisitDate(long id,Date date, Subscription associatedSub) {
        this.id=id;
        this.date = date;
        this.associatedSub = associatedSub;
    }
    public VisitDate(Date date, Subscription associatedSub) {

        this.date = date;
        this.associatedSub = associatedSub;
    }

    public VisitDate() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

