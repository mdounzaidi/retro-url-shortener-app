package com.mdounzaidi.retro.url.shortner.controller;
import com.mdounzaidi.retro.url.shortner.services.URLServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLRedirectController {
   @Autowired
   URLServices urlServices;

    @GetMapping("{myId}")
    public ResponseEntity<?> getURL(@PathVariable("myId") String shortUrl){
        String org=urlServices.findShortUrl(shortUrl);
        if(org!=null){
            if(!org.startsWith("http://") && !org.startsWith("https://")){
                org = "https://" + org; // Default to https
            }
            return ResponseEntity.status(HttpStatus.FOUND).header("Location",org).build();
        }
        else
            return ResponseEntity.notFound().build();
    }
}
