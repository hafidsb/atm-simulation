package com.hafidsb.atm_simulation.models;

import com.hafidsb.atm_simulation.models.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class FundTransfer {
    private Account destinationAccount;
    private int transferAmount;
    private int refNumber;
}
