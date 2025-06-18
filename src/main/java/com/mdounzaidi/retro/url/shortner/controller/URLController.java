package com.mdounzaidi.retro.url.shortner.controller;

import com.mdounzaidi.retro.url.shortner.model.URLEntry;
import com.mdounzaidi.retro.url.shortner.services.URLServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shortURL")
public class URLController {
    @Autowired
    URLServices urlServices;

    @GetMapping("/get-all-url/{userName}")
    public ResponseEntity<?> getAllURL(@PathVariable String userName) {
        try {
            return new ResponseEntity<>(urlServices.findAllUrl(userName),HttpStatus.OK);
        }
         catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{userName}")
    public ResponseEntity<?> addURL(@RequestBody URLEntry url, @PathVariable String userName){
        try{
            String shortUrl = urlServices.saveMyUrl(url,userName);
            return new ResponseEntity<>(shortUrl, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("{userName}")
    public ResponseEntity<?> deleteURL(@RequestParam String url,@PathVariable String userName) {
        try {
            urlServices.deleteMyURL(url, userName);

            return new ResponseEntity<>("successfully deleted", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}