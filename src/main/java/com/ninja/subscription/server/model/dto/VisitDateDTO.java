package com.ninja.subscription.server.model.dto;

import java.util.Date;

public class VisitDateDTO {
    private long id;


    private Date date;

    public VisitDateDTO(long id, Date date) {
        this.id = id;
        this.date = date;
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
