package com.abhay.blog.blogapplication.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "Blog Application is Running";
    }

}
