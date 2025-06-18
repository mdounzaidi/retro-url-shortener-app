package com.mdounzaidi.retro.url.shortner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/healthcheck404")
    String checkhealth(){
        return "ok";
    }
}
