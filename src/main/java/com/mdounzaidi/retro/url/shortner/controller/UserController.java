package com.mdounzaidi.retro.url.shortner.controller;


import com.mdounzaidi.retro.url.shortner.model.User;
import com.mdounzaidi.retro.url.shortner.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/create-new-user")
    public ResponseEntity<?> saveUser(@RequestBody User newUser){
        try{
            userServices.saveNewUser(newUser);
            return new ResponseEntity<>("User Created Successfully",HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>("Invalid error",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-all-user")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userServices.getAllUser(), HttpStatus.OK);
    }

    @PutMapping("/update-user/{pass}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String pass){
        try
        {
            userServices.updateOldUser(user,pass);
            return new ResponseEntity<>("User Updated Successfully",HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Invalid Request",HttpStatus.BAD_REQUEST);
        }
    }

}
