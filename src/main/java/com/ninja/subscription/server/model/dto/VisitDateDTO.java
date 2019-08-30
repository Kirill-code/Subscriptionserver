package com.ninja.subscription.server.model.dto;

import java.time.LocalDateTime;

public class VisitDateDTO {
    private long id;


    private LocalDateTime date;

    public VisitDateDTO(long id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
