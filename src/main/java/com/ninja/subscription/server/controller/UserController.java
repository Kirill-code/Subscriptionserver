package com.ninja.subscription.server.controller;


import com.google.firebase.auth.*;
import com.ninja.subscription.server.model.FirebaseProvider;
import com.ninja.subscription.server.model.FirebaseUsers;

import com.ninja.subscription.server.model.IdentityProvider;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;
import com.ninja.subscription.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/generalusers", method = RequestMethod.GET)
    @ResponseBody
    public List<FirebaseUsers> getUsers(@RequestHeader("token") String idToken) {

        return service.getAll();
    }
    @RequestMapping(value = "/createnewuser", method = RequestMethod.POST)
    @ResponseBody
    public FirebaseUsers createNewUser(@RequestBody FirebaseUsers user) {

        return service.newUser(user);
    }


    @RequestMapping(value = "/email", method = RequestMethod.GET)
    @ResponseBody
    public FirebaseUsers getUserByEmail(/*@RequestHeader("token") String idToken,*/@RequestHeader("email") String email) {
       /* if (Boolean.TRUE.equals(checker.checkUsers(idToken))) {
        }*/
        return service.getByEmail(email);
    }

    private static Logger log = Logger.getLogger(UserController.class.getName());

    IdentityProvider chcker=new FirebaseProvider();

    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    @ResponseBody
    public List<FirebaseUsers> getAdmins(@RequestHeader("token") String idToken) {
        List<FirebaseUsers> list = new ArrayList<>();
        if (Boolean.TRUE.equals(chcker.checkUsers(idToken))) {
            // Start listing users from the beginning, 1000 at a time.
            try {
                ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
                //Create list of Sub Users with data from Firebase: user - firebase object
                while (page != null) {
                    for (ExportedUserRecord user : page.getValues()) {
                        FirebaseUsers tempUser=new FirebaseUsers();
                        tempUser.setUid(user.getUid());
                        tempUser.setEmail(user.getEmail());
                        list.add(tempUser);
                    }
                    page = page.getNextPage();
                }
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
            }
            log.info(" Users list sent " + new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()));

        } else {
            FirebaseUsers tempUser=new FirebaseUsers();
            tempUser.setUid("Auth");
            tempUser.setEmail("Error");
            list.add(tempUser);
        }

        return list;
    }
    @RequestMapping(value = "/adminclaim", method = RequestMethod.POST)
    @ResponseBody
    public String setAdminClaim(@RequestHeader("token") String idToken, @RequestHeader("uid") String uid) throws FirebaseAuthException {
        //TODO go to AUTH interface
        String result="Success";
        UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(uid)
                .setEmail("user2@example.com");

        UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
        /*if (Boolean.TRUE.equals(chcker.checkUsers(idToken))) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("admin", true);

            try {
                UserRecord user = FirebaseAuth.getInstance().getUser(uid);

                if (Boolean.TRUE.equals(user.getCustomClaims().get("admin"))) {
                    resUser=new User("uid","unsuccess. User existed");
                }else {
                    FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
                    resUser=new User("uid","success");
                    log.info(uid+" admnis rights assigned " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));
                }
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
                resUser=new User("","wrong user");
            }
        }else{
        resUser=new User("","wrong user");}*/
        return result;
    }
}
