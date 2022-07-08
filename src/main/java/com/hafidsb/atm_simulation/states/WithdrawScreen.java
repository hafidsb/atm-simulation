package com.hafidsb.atm_simulation.states;

import com.hafidsb.atm_simulation.enums.ATMStateEnum;
import com.hafidsb.atm_simulation.models.ATMSession;
import com.hafidsb.atm_simulation.models.Account;

import java.util.List;

import static com.hafidsb.atm_simulation.enums.ATMStateEnum.*;
import static com.hafidsb.atm_simulation.utils.InputValidation.validateWithdrawFunds;
import static java.lang.Integer.parseInt;

public class WithdrawScreen extends BaseState implements IState{

    @Override
    public void printInitialMessage() {
        System.out.println();
        System.out.println("Withdraw Funds");
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");
        System.out.print("Please choose an option[default is 5]: ");
    }

    @Override
    public ATMStateEnum process(List<Account> accounts, ATMSession session) {
        int intChoice = 5;
        String strChoice = scanner.nextLine();

        try {
            intChoice = parseInt(strChoice);
        } catch (NumberFormatException ex) {
            if (!strChoice.isEmpty()) return WITHDRAW;
        }

        int amount;
        switch (intChoice) {
            case 1 -> amount = 10;
            case 2 -> amount = 50;
            case 3 -> amount = 100;
            case 4 -> {
                return OTHER_WITHDRAW;
            }
            case 5 -> {
                return TRANSACTION;
            }
            default -> {
                System.out.println("Invalid number option!");
                return TRANSACTION;
            }
        }
        if (this.withdrawFunds(session, amount)) {
            return SUMMARY;
        } else {
            return WITHDRAW;
        }
    }
    private boolean withdrawFunds(ATMSession session, int amount) {
        int balance = session.getLoggedAccount().getBalance();
        if (validateWithdrawFunds(amount, balance)) {
            session.getLoggedAccount().setBalance(balance - amount);
            return true;
        }
        return false;
    }
}
