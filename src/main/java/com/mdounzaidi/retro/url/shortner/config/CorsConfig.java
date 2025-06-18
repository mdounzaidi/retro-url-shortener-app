package com.mdounzaidi.retro.url.shortner.config; // adjust package if needed

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all endpoints
                        .allowedOrigins("*") // Allow all origins — or specify 'http://localhost:63342'
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Allow common HTTP methods
            }
        };
    }
}