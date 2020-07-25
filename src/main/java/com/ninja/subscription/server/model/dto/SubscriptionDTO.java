package com.ninja.subscription.server.model.dto;

import java.util.*;

public class SubscriptionDTO {

    private Date saleDate,
            finishDate;

    private long id,
            price,
            instructorId,
            associatedUserId;

    private String
            userName,
            userSurName,
            userMobile,
            description,
            instrName,
            instrSurname, uid;

    private Set<VisitDateDTO> visitDates = new HashSet<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public void setUserSurName(String userSurName) {
        this.userSurName = userSurName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstrName(String instrName) {
        this.instrName = instrName;
    }

    public void setInstrSurname(String instrSurname) {
        this.instrSurname = instrSurname;
    }

    public void setVisitDates(Set<VisitDateDTO> visitDates) {
        this.visitDates = visitDates;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public long getId() {
        return id;
    }

    public long getPrice() {
        return price;
    }

    public long getInstructorId() {
        return instructorId;
    }

    public String getDescription() {
        return description;
    }

    public String getInstrName() {
        return instrName;
    }

    public String getInstrSurname() {
        return instrSurname;
    }

    public Set<VisitDateDTO> getVisitDates() {
        return visitDates;
    }

    public long getAssociatedUserId() {
        return associatedUserId;
    }

    public void setAssociatedUserId(long associatedUserId) {
        this.associatedUserId = associatedUserId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
