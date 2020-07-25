package com.ninja.subscription.server.model.dto;

import java.util.Date;

public class GroupedVisitDatesDTO {
    private long  instr_id, visits_count;
    private Date date;

    public long getInstr_id() {
        return instr_id;
    }

    public void setInstr_id(long instr_id) {
        this.instr_id = instr_id;
    }

    public long getVisits_count() {
        return visits_count;
    }

    public void setVisits_count(long visits_count) {
        this.visits_count = visits_count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
