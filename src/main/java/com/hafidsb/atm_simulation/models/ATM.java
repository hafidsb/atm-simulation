package com.hafidsb.atm_simulation.models;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.hafidsb.atm_simulation.utils.ATMUtil.csvValid;

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

    public boolean generateFromCSV(String csvPath) {
        List<Account> accountList;
        File inputFile = new File(csvPath);

        try(InputStream fileStream = new FileInputStream(inputFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream))) {

            accountList = reader
                    .lines()
                    .skip(1)
                    .map(mapToAccount)
                    .toList();

            if (csvValid(accountList)) {
                this.accounts = accountList;
                return true;
            } else {
                System.out.println("Failed to read the csv file");
                return false;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private Function<String, Account> mapToAccount = line -> {
        String[] tokens = line.split(",");
        return new Account(
                tokens[3],
                tokens[0],
                tokens[1],
                Integer.parseInt(tokens[2])
        );
    };
}
