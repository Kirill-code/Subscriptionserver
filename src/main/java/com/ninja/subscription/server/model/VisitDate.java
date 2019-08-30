package com.ninja.subscription.server.model;

import javax.persistence.*;

@Entity
@Table(name="visitdate")

public class VisitDate {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private long instr_id;

   /* @Column(name = "date", nullable = false)
    private LocalDateTime date;*/

    @ManyToOne
    @JoinColumn(name="subscription_id")
    private Subscription associatedSub;

    public Subscription getAssociatedSub() {
        return associatedSub;
    }

    public void setAssociatedSub(Subscription associatedSub) {
        this.associatedSub = associatedSub;
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInstr_id() {
        return instr_id;
    }

    public void setInstr_id(long instr_id) {
        this.instr_id = instr_id;
    }

    /*public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }*/
}

