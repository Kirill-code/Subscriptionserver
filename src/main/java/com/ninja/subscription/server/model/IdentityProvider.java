package com.ninja.subscription.server.model;

public interface IdentityProvider {
    boolean checkUsers(String idToken);
}
