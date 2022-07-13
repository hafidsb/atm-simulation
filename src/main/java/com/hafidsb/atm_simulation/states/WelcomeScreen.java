package com.hafidsb.atm_simulation.states;

import com.hafidsb.atm_simulation.enums.ATMStateEnum;
import com.hafidsb.atm_simulation.models.ATMSession;
import com.hafidsb.atm_simulation.models.Account;
import com.hafidsb.atm_simulation.utils.AccountUtil;
import lombok.Getter;

import java.util.List;

import static com.hafidsb.atm_simulation.enums.ATMStateEnum.TRANSACTION;
import static com.hafidsb.atm_simulation.enums.ATMStateEnum.WELCOME;
import static com.hafidsb.atm_simulation.utils.InputValidation.validateLogin;

@Getter
public class WelcomeScreen extends BaseState implements IState{
    @Override
    public void printInitialMessage(ATMSession session) {
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
        var account = AccountUtil.loginAccount(registeredAccounts, accountNumber, pin);
        session.setLoggedAccount(account);
        if (session.getLoggedAccount() == null) {
            System.out.println("Invalid Account Number/PIN!\n");
            return WELCOME;
        }

        return TRANSACTION;
    }
}
