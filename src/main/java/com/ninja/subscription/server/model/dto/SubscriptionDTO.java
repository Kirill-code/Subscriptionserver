package com.ninja.subscription.server.model.dto;

import com.ninja.subscription.server.model.Instructor;
import com.ninja.subscription.server.model.VisitDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriptionDTO {

    public SubscriptionDTO(long id, long price, String userid, String description, Date saleDate,Date finishDate,InstructorDTO associatedInstructor, List<VisitDateDTO> visitDate) {
        this.id = id;
        this.price = price;
        this.userid = userid;
        this.description = description;
        this.saleDate = saleDate;
        this.finishDate=finishDate;
        this.visitDates=visitDate;
        this.associatedInstructor=associatedInstructor;
        this.instructorId=associatedInstructor.getId();
        this.instrName=associatedInstructor.getName();
        this.instrSurname=associatedInstructor.getSurname();
    }

    private long id, price, instructorId;

    private String userid;
    private String description;
    private String instrName;

    public long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstrName() {
        return instrName;
    }

    public void setInstrName(String instrName) {
        this.instrName = instrName;
    }

    public String getInstrSurname() {
        return instrSurname;
    }

    public void setInstrSurname(String instrSurname) {
        this.instrSurname = instrSurname;
    }

    private String instrSurname;

    private Date saleDate, finishDate;


    private List<VisitDateDTO> visitDates = new ArrayList<>();

    private InstructorDTO associatedInstructor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<VisitDateDTO> getVisitDates() {
        return visitDates;
    }

    public void setVisitDates(List<VisitDateDTO> visitDates) {
        this.visitDates = visitDates;
    }
}
