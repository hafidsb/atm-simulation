package com.hafidsb.atm_simulation.states;

import com.hafidsb.atm_simulation.enums.ATMStateEnum;
import com.hafidsb.atm_simulation.models.ATMSession;
import com.hafidsb.atm_simulation.models.Account;

import java.util.List;

import static com.hafidsb.atm_simulation.enums.ATMStateEnum.*;
import static java.lang.Integer.parseInt;

public class FundTransferSummaryScreen extends BaseState implements IState{

    @Override
    public void printInitialMessage(ATMSession session) {
        System.out.println();
        System.out.println("Fund Transfer Summary");
        System.out.println("Destination Account: " + session.getLatestTransfer().getSenderId());
        System.out.println("Transfer Amount: " + session.getLatestTransfer().getAmount());
        System.out.println("Reference Number: " + session.getLatestTransfer().getReferenceNumber());
        System.out.println("Current Balance: " + session.getLoggedAccount().getBalance());

        System.out.println();
        System.out.println("Please enter destination account and press enter to continue or ");
        System.out.print("press enter to go back to Transaction: ");
    }

    @Override
    public ATMStateEnum process(List<Account> accounts, ATMSession session) {
        int intChoice = 2;
        String strChoice = scanner.nextLine();

        try {
            intChoice = parseInt(strChoice);
        } catch (NumberFormatException ex) {
            if (!strChoice.isEmpty()) return FUND_TRANSFER_SUMMARY;
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
                return FUND_TRANSFER;
            }
        }
    }
}
