package com.ninja.subscription.server.model;

public class User {
    String uid,email;
    boolean adminClaim;

    public boolean getClaim() {
        return adminClaim;
    }

    public void setClaim(boolean claim) {
        this.adminClaim = claim;
    }

    public User(String uid, String email, boolean adminClaim) {
        this.uid = uid;
        this.email = email;
        this.adminClaim=adminClaim;
    }


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
}
