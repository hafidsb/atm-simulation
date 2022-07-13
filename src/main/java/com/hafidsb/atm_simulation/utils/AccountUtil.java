package com.hafidsb.atm_simulation.utils;

import com.hafidsb.atm_simulation.models.Account;

import java.util.List;

public class AccountUtil {
    private AccountUtil() {}

    public static Account loginAccount(List<Account> accounts, String accountNumber, String pin) {
        for (Account account : accounts) {
            if (account.getId().equals(accountNumber) &&
                    account.getPin().equals(pin)) {
                return account;
            }
        }

        return null;
    }

    public static Account findAccount(List<Account> accounts, String accountNumber) {
        for (Account account : accounts) {
            if (account.getId().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
