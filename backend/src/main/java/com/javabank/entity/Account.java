package com.javabank.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity 
@Table(name = "accounts")
public class Account {
   @Id 
   @Column(name = "account_name") 
    private String account_name;
   
   @Id 
   @Column(name = "account_id")
   private String account_id; 
    
   @Id 
   @Column(name = "pin_hash")
   private String pin_hash;

   @Id 
   @Column(name = "balance")
   private BigDecimal balance;
}
