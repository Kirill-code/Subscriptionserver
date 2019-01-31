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

import static java.lang.Boolean.*;

@RestController
public class UserController {


    private static Logger log = Logger.getLogger(UserController.class.getName());

    //?????????where put it????
    boolean checkUsers(String idToken) {
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
                        list.add(new User(user.getUid(), user.getEmail()));
                    }
                    page = page.getNextPage();
                }
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
                list.add(new User("Auth","Error"));
            }
            log.info(" Users list sent " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));

        } else {

            list.add(new User("Auth","Error"));

        }


        return list;
    }
    @RequestMapping(value = "/adminclaim", method = RequestMethod.POST)
    @ResponseBody
    public User setAdminClaim(@RequestHeader("token") String idToken, @RequestHeader("uid") String uid) throws FirebaseAuthException {
        User resUser;
        if (checkUsers(idToken)) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("admin", true);

            try {
                UserRecord user = FirebaseAuth.getInstance().getUser(uid);
                System.out.println(user.getEmail()+" Have it ?");
                if (Boolean.TRUE.equals(user.getCustomClaims().get("admin"))) {
                    System.out.println(" yes");
                    resUser=new User("uid","unsuccess. User existed");
                }else {
                    System.out.println(" no");
                    FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
                    resUser=new User("uid","success");
                    log.info(uid+" admnis rights assigned " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));
                }
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
                resUser=new User("","wrong user");
            }
        }else{
        resUser=new User("","wrong user");}
        return resUser;
    }
}