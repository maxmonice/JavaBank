package com.javabank.dto;

public class AccountRequest {
    private String accountName;
    private String accountPin;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPin() {
        return accountPin;
    }
    public void setPin(String accountPin) {
        this.accountPin = accountPin;
    }
}
