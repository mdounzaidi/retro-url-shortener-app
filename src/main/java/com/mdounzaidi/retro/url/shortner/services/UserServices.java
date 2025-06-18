package com.mdounzaidi.retro.url.shortner.services;

import com.mdounzaidi.retro.url.shortner.model.User;
import com.mdounzaidi.retro.url.shortner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepo;

    public  List<User> getAllUser() {
        return userRepo.findAll();
    }

    public void saveNewUser(User newUser) {
        if(newUser.getUserName()!=null&&newUser.getPassword()!=null){
            userRepo.save(newUser);
        }
        else {
            throw new RuntimeException("Invalid username or password");
        }
    }


    public void updateOldUser(User user,String newPassword) {
        User oldUser=userRepo.findUserByUserName(user.getUserName());

        if(oldUser==null){
            throw new RuntimeException("Invalid UserName");
        }
        if(!oldUser.getPassword().equals(user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        oldUser.setPassword(newPassword);
        userRepo.save(oldUser);
    }
}
