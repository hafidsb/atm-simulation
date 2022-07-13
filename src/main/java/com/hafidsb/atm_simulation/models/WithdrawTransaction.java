package com.hafidsb.atm_simulation.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class WithdrawTransaction extends Transaction {
    private String withdrawId;

    public WithdrawTransaction(String referenceNumber, int amount, LocalDateTime timestamp, String withdrawId) {
        super(referenceNumber, amount, timestamp);
        this.withdrawId = withdrawId;
    }
}
