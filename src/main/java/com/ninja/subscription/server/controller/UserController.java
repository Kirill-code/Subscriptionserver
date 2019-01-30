package com.ninja.subscription.server.controller;

import com.google.firebase.auth.*;
import com.ninja.subscription.server.model.User;
import com.ninja.subscription.server.service.SubscriptionService;
import com.ninja.subscription.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@RestController
public class UserController {


    private static Logger log = Logger.getLogger(UserController.class.getName());

    //?????????where put it????
    boolean checkUsers(String idToken) {
        FirebaseToken decodedToken = null;
        boolean result;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            System.out.println(decodedToken.getClaims());
            log.info(decodedToken.getEmail() + " logged at " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));
            result = true;
        } catch (FirebaseAuthException e) {
            log.info("Auth error at " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));

            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers(@RequestHeader("token") String idToken) {
        List<User> list = new ArrayList<>();
        if (checkUsers(idToken)) {
            // Start listing users from the beginning, 1000 at a time.
            try {
                ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
                while (page != null) {
                    for (ExportedUserRecord user : page.getValues()) {
                        boolean claim=Boolean.valueOf(user.getCustomClaims().get("testad").toString());
                        list.add(new User(user.getUid(), user.getEmail(),claim));
                    }
                    page = page.getNextPage();
                }
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
                list.add(new User("Auth","Error",false));
            }
            log.info(" Users list sent " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));

        } else {

            list.add(new User("Auth","Error",false));

        }


        return list;
    }
    @RequestMapping(value = "/adminclaim", method = RequestMethod.POST)
    @ResponseBody
    public void setAdminClaim(@RequestHeader("token") String idToken, @RequestHeader("uid") String uid) throws FirebaseAuthException {
        List<User> list = new ArrayList<>();
        if (checkUsers(idToken)) {
            System.out.println(FirebaseAuth.getInstance().getUser(uid).getCustomClaims());
            Map<String, Object> claims = new HashMap<>();
            claims.put("admin", true);
            try {
                FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
                System.out.println("Success");

                UserRecord user = FirebaseAuth.getInstance().getUser(uid);
                System.out.println(user.getCustomClaims().get("admin"));
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
            }
        }
    }
}
