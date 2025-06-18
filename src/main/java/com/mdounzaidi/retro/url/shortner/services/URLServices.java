package com.mdounzaidi.retro.url.shortner.services;


import com.mdounzaidi.retro.url.shortner.model.URLEntry;
import com.mdounzaidi.retro.url.shortner.model.User;
import com.mdounzaidi.retro.url.shortner.repository.URLRepository;
import com.mdounzaidi.retro.url.shortner.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class URLServices {
    @Autowired
    URLRepository urlRepo;
    @Autowired
    UserRepository userRepo;

    public List<URLEntry> findAllUrl(String  userName) {
        User user=userRepo.findUserByUserName(userName);
        if(user==null){
            throw new RuntimeException("User not found");
        }
        return user.getUrlList();
    }
    @Transactional
    public String saveMyUrl(URLEntry url,String userName){
        User oldUser=userRepo.findUserByUserName(userName);
        if(oldUser==null){
            throw new RuntimeException("User not found");
        }
        String shortLink = RandomStringUtils.randomAlphanumeric(5);
        while (urlRepo.findByShortUrl(shortLink)!=null){
            shortLink = RandomStringUtils.randomAlphanumeric(5);
        }
        shortLink="localhost:8080/"+shortLink;
        url.setShortUrl(shortLink);
        url.setStartDate(LocalDate.now());
        url.setEndDate(LocalDate.now().plusDays(100));
        urlRepo.save(url);
        oldUser.getUrlList().add(url);
        userRepo.save(oldUser);
        return shortLink;
    }

    public String findShortUrl(String shortUrl){
        URLEntry url= urlRepo.findByShortUrl(shortUrl);
        if(url!=null){
            if(url.getEndDate().isAfter(LocalDate.now())){
                return url.getOriginalURL();
            }
        }
        return null;
    }

    @Transactional
    public void deleteMyURL(String url,String userName) {
        URLEntry url1 = urlRepo.findByOriginalURL(url);
        User user=userRepo.findUserByUserName(userName);
        if (user==null){
            throw new RuntimeException("User not found");
        }
        if (url1 != null) {
            urlRepo.delete(url1);
            user.getUrlList().remove(url1);
            userRepo.save(user);
        } else {
            throw new RuntimeException("Url not found");
        }
    }
}