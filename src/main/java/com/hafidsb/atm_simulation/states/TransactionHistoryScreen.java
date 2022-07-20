package com.hafidsb.atm_simulation.states;

import com.hafidsb.atm_simulation.enums.ATMStateEnum;
import com.hafidsb.atm_simulation.models.ATMSession;
import com.hafidsb.atm_simulation.models.Account;

import java.util.List;

public class TransactionHistoryScreen extends BaseState implements IState {
    @Override
    public void printInitialMessage(ATMSession session) {
        System.out.println();
        System.out.println("Here are Your last 10 transactions: ");
    }

    @Override
    public ATMStateEnum process(List<Account> accounts, ATMSession session) {
        return null;
    }
}
