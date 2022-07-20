package com.hafidsb.atm_simulation.utils;

import com.hafidsb.atm_simulation.models.Account;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ATMUtil {
    private ATMUtil() {}

    public static boolean csvValid(List<Account> accounts) {
        Set<Account> tempAccountList = new HashSet<>();
        Set<String> tempNumberList = new HashSet<>();

        for (Account account : accounts) {
            if (!tempAccountList.add(account)) {
                System.out.println("There can't be duplicated records: ");
                System.out.println(account);
                return false;
            }

            if (!tempNumberList.add(account.getId())) {
                System.out.println("There can't be 2 different accounts with the same Account Number: " + account.getId());
                return false;
            }
        }

        return true;
    }
}
