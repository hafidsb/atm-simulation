package com.hafidsb.atm_simulation.states;

import com.hafidsb.atm_simulation.enums.ATMStateEnum;
import com.hafidsb.atm_simulation.models.ATMSession;
import com.hafidsb.atm_simulation.models.Account;

import java.util.List;

public interface IState {
    void printInitialMessage(ATMSession session);

    /**
     * main functionality of each state
     * will return true on success processes
     * and false on failed ones
     */
    ATMStateEnum process(List<Account> accounts, ATMSession session);
}
