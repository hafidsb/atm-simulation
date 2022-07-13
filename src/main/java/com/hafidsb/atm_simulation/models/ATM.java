package com.hafidsb.atm_simulation.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class ATM {
    private List<Account> accounts;
    private List<TransferTransaction> transferList;
    private List<WithdrawTransaction> withdrawList;

    public ATM() {
        this.accounts = this.generateInitialAccounts();
        this.transferList = new ArrayList<>();
        this.withdrawList = new ArrayList<>();
    }

    public void addWithdraw(WithdrawTransaction withdrawTransaction) {
        this.withdrawList.add(withdrawTransaction);
    }

    public void addTransfer(TransferTransaction transferTransaction) {
        this.transferList.add(transferTransaction);
    }

    public List<Account> generateInitialAccounts() {
        Account account1 = new Account("112233", "John Doe", "012118", 1100);
        Account account2 = new Account("112244", "jane Doe", "932012", 300);
        return Arrays.asList(account1, account2);
    }
}
