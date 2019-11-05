package com.ninja.subscription.server.model.dto;

import java.util.Date;

public class VisitDateDTO {
    private long id, time;


    private Date date;

    public VisitDateDTO(long id, Date date, long time) {
        this.id = id;
        this.date = date;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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
