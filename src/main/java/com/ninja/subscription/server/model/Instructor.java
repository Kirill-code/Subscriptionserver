package com.ninja.subscription.server.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="instructor")
@Proxy(lazy = false)
public class Instructor {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "instructor_id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "uid", nullable = false)
    private String uid;

    public List<Subscription> getInstructorSubscriptions() {
        return instructorSubscriptions;
    }

    public void setInstructorSubscriptions(List<Subscription> instructorSubscriptions) {
        this.instructorSubscriptions = instructorSubscriptions;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "associatedInstructor", orphanRemoval=true)
    private List<Subscription> instructorSubscriptions= new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name= "instr_id")
    private List<VisitDate> visitInstr = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String password) {
        this.surname = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}