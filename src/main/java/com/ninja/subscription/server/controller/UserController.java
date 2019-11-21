package com.ninja.subscription.server.controller;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.ninja.subscription.server.model.FirebaseProvider;
import com.ninja.subscription.server.model.FirebaseUsers;
import com.ninja.subscription.server.model.IdentityProvider;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserController {


    private static Logger log = Logger.getLogger(UserController.class.getName());

    IdentityProvider chcker=new FirebaseProvider();

    @RequestMapping(value = "/fireusers", method = RequestMethod.GET)
    @ResponseBody
    public List<FirebaseUsers> getUsers(@RequestHeader("token") String idToken) {
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

            log.info(" Users list sent " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));

        } else {
            FirebaseUsers tempUser=new FirebaseUsers();
            tempUser.setUid("Auth");
            tempUser.setEmail("Error");
            list.add(tempUser);
        }

        return list;
    }


}
