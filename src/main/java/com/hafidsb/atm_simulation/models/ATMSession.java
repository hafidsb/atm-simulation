package com.hafidsb.atm_simulation.models;

import com.hafidsb.atm_simulation.enums.ATMStateEnum;
import com.hafidsb.atm_simulation.states.IState;
import com.hafidsb.atm_simulation.states.WelcomeScreen;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ATMSession {
    private ATM atm;
    private ATMStateEnum stateEnum;
    private IState stateObject;
    private Account loggedAccount;

    public ATMSession() {
        this.atm = new ATM();
        this.stateEnum = ATMStateEnum.WELCOME;
        this.stateObject = new WelcomeScreen();
    }

    public void executeStateProcess() {
        this.stateObject.printInitialMessage();
        var accounts = this.atm.getAccounts();
        var nextStateEnum = this.stateObject.process(accounts, this);
        this.setStateEnum(nextStateEnum);
    }
}