package com.javabank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class HelloController {

        @GetMapping("/")
        public String hello() {
            return "JavaBank API is running!";
        }
        @GetMapping("/home")
        public String home() {
            return "Welcome to JavaBank Homepage!";
        }
    
    }    

