package com.hafidsb.atm_simulation.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class WithdrawTransaction extends Transaction {
    private String withdrawerId;

    public WithdrawTransaction(String referenceNumber, int amount, LocalDateTime timestamp, String withdrawerId) {
        super(referenceNumber, amount, timestamp);
        this.withdrawerId = withdrawerId;
    }
}
