package com.hafidsb.atm_simulation.states;

import com.hafidsb.atm_simulation.enums.ATMStateEnum;
import com.hafidsb.atm_simulation.models.ATMSession;
import com.hafidsb.atm_simulation.models.Account;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.hafidsb.atm_simulation.enums.ATMStateEnum.*;
import static java.lang.Integer.parseInt;

public class SummaryScreen extends BaseState implements IState{
    @Override
    public void printInitialMessage(ATMSession session) {
        System.out.println();
        System.out.println("Withdraw Summary");
        System.out.println("Date: " + session.getLatestTransaction().getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")));
        System.out.println("Withdraw: " + session.getLatestTransaction().getAmount());
        System.out.println("Current Balance: " + session.getLoggedAccount().getBalance());

        System.out.println();
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Please choose an option[default is 2]: ");
    }

    @Override
    public ATMStateEnum process(List<Account> accounts, ATMSession session) {
        int intChoice = 2;
        String strChoice = scanner.nextLine();

        try {
            intChoice = parseInt(strChoice);
        } catch (NumberFormatException ex) {
            if (!strChoice.isEmpty()) return SUMMARY;
        }

        switch (intChoice) {
            case 1 -> {
                return TRANSACTION;
            }
            case 2 -> {
                return WELCOME;
            }
            default -> {
                System.out.println("Invalid number option!");
                return SUMMARY;
            }
        }
    }
}
