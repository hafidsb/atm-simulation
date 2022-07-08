package com.hafidsb.atm_simulation.states;

import com.hafidsb.atm_simulation.enums.ATMStateEnum;
import com.hafidsb.atm_simulation.models.ATMSession;
import com.hafidsb.atm_simulation.models.Account;

import java.util.List;

import static com.hafidsb.atm_simulation.enums.ATMStateEnum.*;
import static java.lang.Integer.parseInt;

public class TransactionScreen extends BaseState implements IState{

    @Override
    public void printInitialMessage() {
        System.out.println();
        System.out.println("Login Success");
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");
        System.out.print("Please choose an option[default is 3]: ");
    }

    @Override
    public ATMStateEnum process(List<Account> accounts, ATMSession session) {
        int intChoice = 3;
        String strChoice = scanner.nextLine();

        try {
            intChoice = parseInt(strChoice);
        } catch (NumberFormatException ex) {
            if (!strChoice.isEmpty()) return TRANSACTION;
        }

        switch (intChoice) {
            case 1 -> {
                return WITHDRAW;
            }
            case 2 -> {
                return FUND_TRANSFER;
            }
            case 3 -> {
                System.out.println("Logged out");
                return WELCOME;
            }
            default -> {
                System.out.println("Invalid number option!");
                return TRANSACTION;
            }
        }
    }
}
