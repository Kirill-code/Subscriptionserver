package com.ninja.subscription.server.controller;


import com.google.firebase.auth.*;
import com.ninja.subscription.server.model.FirebaseProvider;
import com.ninja.subscription.server.model.FirebaseUsers;

import com.ninja.subscription.server.model.IdentityProvider;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;
import com.ninja.subscription.server.model.dto.UsersDTO;
import com.ninja.subscription.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
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
    public List<UsersDTO> getAdmins(@RequestHeader("token") String idToken) {
        List<UsersDTO> list = new ArrayList<>();
        if (Boolean.TRUE.equals(chcker.checkUsers(idToken))) {
            // Start listing users from the beginning, 1000 at a time.
            try {
                ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
                //Create list of Sub Users with data from Firebase: user - firebase object
                while (page != null) {
                    for (ExportedUserRecord user : page.getValues()) {
                        UsersDTO tempUser=new UsersDTO();
                        tempUser.setUid(user.getUid());
                        tempUser.setEmail(user.getEmail());
                        tempUser.setClaim((Boolean) user.getCustomClaims().get("instructor")!=null?true:false);
                        list.add(tempUser);
                    }
                    page = page.getNextPage();
                }

            } catch (FirebaseAuthException e) {
                e.printStackTrace();
            }
            log.info(" Users list sent " + new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()));

       } else {
            UsersDTO tempUser=new UsersDTO();
            tempUser.setUid("Auth");
            tempUser.setEmail("Error");
            list.add(tempUser);
        }
        Collections.sort(list, Collections.reverseOrder());

        return list;
    }
    @RequestMapping(value = "/adminclaim", method = RequestMethod.POST)
    @ResponseBody
    public String setAdminClaim(@RequestHeader("token") String idToken, @RequestHeader("uid") String uid) {
       String result;
       try{
           Map<String, Object> claims = new HashMap<>();
           claims.put("instructor", true);
           FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
           result="Claims added";

           log.info(" Claims added for  "+uid+" " + new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()));

       }catch (FirebaseAuthException ex){
           result="Claim error";
       }


        return result;
    }
    @RequestMapping(value = "/removeadminclaim", method = RequestMethod.DELETE)
    @ResponseBody
    public String renoveAdminClaim(@RequestHeader("token") String idToken, @RequestHeader("uid") String uid)  {
        String result;
        try{
            Map<String, Object> claims = new HashMap<>();
            claims.remove("instructor", true);
            FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
            result="Claims removed";

            log.info(" Claims removed for  "+uid+" " + new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()));

        }catch (FirebaseAuthException ex){
            result="Claim error";
        }


        return result;
    }
}
