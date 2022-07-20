package com.hafidsb.atm_simulation.states;


import com.hafidsb.atm_simulation.enums.ATMStateEnum;
import com.hafidsb.atm_simulation.models.ATMSession;
import com.hafidsb.atm_simulation.models.Account;

import java.util.List;

import static com.hafidsb.atm_simulation.enums.ATMStateEnum.*;
import static com.hafidsb.atm_simulation.utils.AccountUtil.findAccount;
import static com.hafidsb.atm_simulation.utils.InputValidation.validateTransferFundsInput;
import static com.hafidsb.atm_simulation.utils.TransactionUtil.*;
import static java.lang.Integer.parseInt;

public class FundTransferScreen extends BaseState implements IState{
    @Override
    public void printInitialMessage(ATMSession session) {
        System.out.println();
        System.out.println("-= Fund Transfer =-");
        System.out.println("Please enter destination account and press enter to continue or ");
        System.out.print("press enter to go back to Transaction: ");
    }

    @Override
    public ATMStateEnum process(List<Account> accounts, ATMSession session) {
        String strDestinationNumber = scanner.nextLine();
        if (strDestinationNumber.isEmpty()) return TRANSACTION;

        System.out.println();
        System.out.println("Please enter transfer amount and press enter to continue or");
        System.out.print("press enter to go back to Transaction: ");

        String strTransferAmount = scanner.nextLine();
        if (strTransferAmount.isEmpty()) return TRANSACTION;

        String refNumber = generateRefNumber();

        System.out.println();
        System.out.println("Transfer Confirmation");
        System.out.println("Destination Account: " + strDestinationNumber);
        System.out.println("Transfer Amount: $" + strTransferAmount);
        System.out.println("Reference Number: " + refNumber);
        System.out.println();
        System.out.println("1. Confirm Transaction");
        System.out.println("2. Cancel Transaction");
        System.out.print("Please choose an option[default is 2]: ");

        int intChoice = 2;
        String strChoice = scanner.nextLine();

        try {
            intChoice = parseInt(strChoice);
        } catch (NumberFormatException ex) {
            if (!strChoice.isEmpty()) return FUND_TRANSFER;
        }

        switch (intChoice) {
            case 1 -> {
                if (!validateTransferFundsInput(session, strDestinationNumber, strTransferAmount)) {
                    return FUND_TRANSFER;
                }

                int amount = parseInt(strTransferAmount);
                Account destination = findAccount(session.getAtm().getAccounts(), strDestinationNumber);
                if (!canTransferFunds(session, amount)) {
                    return FUND_TRANSFER;
                }

                assert destination != null;
                transferFunds(session, amount, destination);

                return FUND_TRANSFER_SUMMARY;
            }
            case 2 -> {
                return TRANSACTION;
            }
            default -> {
                System.out.println("Invalid number option!");
                return FUND_TRANSFER;
            }
        }
    }
}
