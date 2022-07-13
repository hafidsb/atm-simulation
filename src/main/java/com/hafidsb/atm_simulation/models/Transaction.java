package com.hafidsb.atm_simulation.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public abstract class Transaction {
    private String referenceNumber;
    private int amount;
    private LocalDateTime timestamp;

    protected Transaction(String referenceNumber, int amount, LocalDateTime timestamp) {
        this.referenceNumber = referenceNumber;
        this.amount = amount;
        this.timestamp = timestamp;
    }
}
