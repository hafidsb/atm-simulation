package com.hafidsb.atm_simulation.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Account {
    private String id;
    private String name;
    private String pin;
    private int balance;

    public void addBalance(int amount) {
        this.balance += amount;
    }

    public void reduceBalance(int amount) {
        this.balance += amount;
    }
}
