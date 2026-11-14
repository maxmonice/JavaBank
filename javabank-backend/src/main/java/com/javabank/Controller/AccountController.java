package com.javabank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javabank.dto.AccountRequest;


@RestController
@RequestMapping("/api/")
public class AccountController {
    @GetMapping("/accounts")
    public String Hello() {
        return "Welcome to Accounts!";
    }

    @PostMapping("/accounts")
    public String createAccount(@RequestBody AccountRequest request) {
        return "Account Received: " + request.getAccountName();
    }
}
