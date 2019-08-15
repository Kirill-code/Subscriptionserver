package com.ninja.subscription.server.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class FirebaseProvider implements IdentityProvider{
    private static Logger log = Logger.getLogger(Utils.class.getName());

    @Override
    public boolean checkUsers(String idToken) {
        FirebaseToken decodedToken = null;
        boolean result;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

            log.info(decodedToken.getEmail() + " logged at " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));
            result = true;
        } catch (FirebaseAuthException e) {
            log.info("Auth error at " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));

            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
