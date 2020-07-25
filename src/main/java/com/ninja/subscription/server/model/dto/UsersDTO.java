package com.ninja.subscription.server.model.dto;

public class UsersDTO  implements Comparable<UsersDTO>{
    String uid, email, displayedName;
    Boolean claim;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getClaim() {
        return claim;
    }

    public void setClaim(Boolean claim) {
        this.claim = claim;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }

    @Override
    public int compareTo(UsersDTO o) {
        return this.getClaim().compareTo(o.getClaim());
    }
}
