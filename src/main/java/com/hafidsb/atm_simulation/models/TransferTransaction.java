package com.hafidsb.atm_simulation.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TransferTransaction extends Transaction {
    private String senderId;
    private String receiverId;

    public TransferTransaction(
            String referenceNumber,
            int amount,
            LocalDateTime timestamp,
            String senderId,
            String receiverId
    ) {
        super(referenceNumber, amount, timestamp);
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
