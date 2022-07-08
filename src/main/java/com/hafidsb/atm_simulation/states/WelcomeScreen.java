package com.hafidsb.atm_simulation.states;

import com.hafidsb.atm_simulation.enums.ATMStateEnum;
import com.hafidsb.atm_simulation.models.ATMSession;
import com.hafidsb.atm_simulation.models.Account;
import lombok.Getter;

import java.util.List;

import static com.hafidsb.atm_simulation.enums.ATMStateEnum.TRANSACTION;
import static com.hafidsb.atm_simulation.enums.ATMStateEnum.WELCOME;
import static com.hafidsb.atm_simulation.utils.InputValidation.validateLogin;

@Getter
public class WelcomeScreen extends BaseState implements IState{
    @Override
    public void printInitialMessage() {
        System.out.println("Welcome to the ATM Simulation!");
    }

    @Override
    public ATMStateEnum process(List<Account> registeredAccounts, ATMSession session) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        if (!validateLogin(accountNumber, "Account Number")) {
            return WELCOME;
        }

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        if (!validateLogin(pin, "PIN")) {
            return WELCOME;
        }

        session.setLoggedAccount(findAccount(registeredAccounts, accountNumber, pin));
        if (session.getLoggedAccount() == null) {
            System.out.println("Invalid Account Number/PIN!\n");
            return WELCOME;
        }

        return TRANSACTION;
    }

    private Account findAccount(List<Account> accounts, String accountNumber, String pin) {
        for (Account account : accounts) {
            if (account.getId().equals(accountNumber) &&
                    account.getPin().equals(pin)) {
                return account;
            }
        }

        return null;
    }
}
