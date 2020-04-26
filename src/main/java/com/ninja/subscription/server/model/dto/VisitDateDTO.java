package com.ninja.subscription.server.model.dto;

import java.util.Date;

public class VisitDateDTO {
    private long id, time, instr_id;
    private String uid;

    private Date date;

    public VisitDateDTO(long id, Date date, long time, long instr_id) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.instr_id=instr_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getInstr_id() {
        return instr_id;
    }

    public void setInstr_id(long instr_id) {
        this.instr_id = instr_id;
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
