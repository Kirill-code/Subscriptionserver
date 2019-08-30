package com.ninja.subscription.server.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import java.util.logging.Logger;

public class FirebaseProvider implements IdentityProvider{
    private static Logger log = Logger.getLogger(Utils.class.getName());

    @Override
    public boolean checkUsers(String idToken) {
        FirebaseToken decodedToken = null;
        boolean result;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

            log.info(decodedToken.getEmail() + " logged at " +Utils.getTimeStamp());
            result = true;
        } catch (FirebaseAuthException e) {
            log.info("Auth error at " + Utils.getTimeStamp());

            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
