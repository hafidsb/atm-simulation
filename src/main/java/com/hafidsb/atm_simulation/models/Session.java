package com.hafidsb.atm_simulation.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Getter @Setter
public class Session {
    private Account loggedAccount;
    private boolean running;
    private boolean inWelcomeScreen;
    private boolean inTransactionScreen;
    private boolean inWithdrawScreen;
    private boolean inOtherWithdrawScreen;
    private boolean inSummaryScreen;
    private boolean inFundTransferScreen;
    private boolean inFundTransferSummaryScreen;
    private Scanner sessionScanner;
    private int latestWithdrawAmount;
    private LocalDateTime latestWithdrawDateTime;
    private FundTransfer latestFundTransfer;

    public Session() {
        this.running = true;
        this.inWelcomeScreen = true;
        this.sessionScanner = new Scanner(System.in);
    }

    public void processLogin(List<Account> registeredAccounts) {
        System.out.print("Enter com.hafidsb.atm_simulation.models.Account Number: ");
        String accountNumber = sessionScanner.nextLine();
        if (invalidLogin(accountNumber, "com.hafidsb.atm_simulation.models.Account Number")) return;

        System.out.print("Enter PIN: ");
        String pin = sessionScanner.nextLine();
        if (invalidLogin(pin, "PIN")) return;

        this.loggedAccount = findAccount(registeredAccounts, accountNumber, pin);
        if (this.loggedAccount == null) {
            System.out.println("Invalid com.hafidsb.atm_simulation.models.Account Number/PIN!\n");
            return;
        }

        this.inWelcomeScreen = false;
        this.inTransactionScreen = true;
    }

    public void processTransaction() {
        int intChoice = 3;
        String strChoice = this.sessionScanner.nextLine();

        try {
            intChoice = Integer.parseInt(strChoice);
        } catch (NumberFormatException ex) {
            if (!strChoice.isEmpty()) return;
        }

        switch (intChoice) {
            case 1 -> this.inWithdrawScreen = true;
            case 2 -> this.inFundTransferScreen = true;
            case 3 -> {
                System.out.println("Logged out");
                this.inWelcomeScreen = true;
            }
        }
        this.inTransactionScreen = false;
    }

    public void processWithdraw() {
        int intChoice = 5;
        String strChoice = sessionScanner.nextLine();

        try {
            intChoice = Integer.parseInt(strChoice);
        } catch (NumberFormatException ex) {
            if (!strChoice.isEmpty()) return;
        }

        switch (intChoice) {
            case 1 -> {
                this.inSummaryScreen = this.withdrawFunds(10);
                if (!this.inSummaryScreen) return;
            }
            case 2 -> {
                this.inSummaryScreen = this.withdrawFunds(50);
                if (!this.inSummaryScreen) return;
            }
            case 3 -> {
                this.inSummaryScreen = this.withdrawFunds(100);
                if (!this.inSummaryScreen) return;
            }
            case 4 -> this.inOtherWithdrawScreen = true;
            case 5 -> this.inTransactionScreen = true;
        }
        this.inWithdrawScreen = false;
    }

    public void processOtherWithdraw() {
        int funds = 0;
        String strFunds = sessionScanner.nextLine();
        try {
            funds = Integer.parseInt(strFunds);
            if (funds % 10 != 0) {
                System.out.println("Invalid amount!");
                return;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid amount!");
            if (!strFunds.isEmpty()) return;
        }

        this.inSummaryScreen = this.withdrawFunds(funds);
        if (!this.inSummaryScreen) return;
        this.inOtherWithdrawScreen = false;
    }

    public void processSummary() {
        int intChoice = 2;
        String strChoice = sessionScanner.nextLine();

        try {
            intChoice = Integer.parseInt(strChoice);
        } catch (NumberFormatException ex) {
            if (!strChoice.isEmpty()) return;
        }

        switch (intChoice) {
            case 1 -> this.inTransactionScreen = true;
            case 2 -> this.inWelcomeScreen = true;
        }
        this.inSummaryScreen = false;
    }

    public void printWithdrawSummary() {
        System.out.println();
        System.out.println("Withdraw Summary");
        System.out.println("Date: " + latestWithdrawDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")));
        System.out.println("Withdraw: " + latestWithdrawAmount);
        System.out.println("Current Balance: " + this.loggedAccount.getBalance());
    }

    public void processFundTransfer(List<Account> registeredAccount) {
        String strAccountNumber = sessionScanner.nextLine();

        if (quitTransferFund(strAccountNumber)) return;

        System.out.println();
        System.out.println("Please enter transfer amount and press enter to continue or");
        System.out.print("press enter to go back to Transaction: ");

        String transferAmount = sessionScanner.nextLine();

        if (quitTransferFund(transferAmount)) return;

        Random rnd = new Random();
        int referenceNumber = 100_000 + rnd.nextInt(900_000);

        System.out.println();
        System.out.println("Transfer Confirmation");
        System.out.println("Destination com.hafidsb.atm_simulation.models.Account: " + strAccountNumber);
        System.out.println("Transfer Amount: $" + transferAmount);
        System.out.println("Reference Number: " + referenceNumber);
        System.out.println();
        System.out.println("1. Confirm Transaction");
        System.out.println("2. Cancel Transaction");
        System.out.print("Please choose an option[default is 2]: ");

        int intChoice = 2;
        String strChoice = sessionScanner.nextLine();

        try {
            intChoice = Integer.parseInt(strChoice);
        } catch (NumberFormatException ex) {
            if (!strChoice.isEmpty()) return;
        }

        switch (intChoice) {
            case 1 -> {
                Account destinationAccount = findAccount(registeredAccount, strAccountNumber);
                if (destinationAccount == null || !isNumeric(strAccountNumber)) {
                    System.out.println("Invalid account!");
                    return;
                }

                if (destinationAccount.equals(loggedAccount)) {
                    System.out.println("Cannot transfer to own account!");
                    return;
                }

                if (isNumeric(transferAmount)) {
                    int balance = this.getLoggedAccount().getBalance();
                    int intAmount = Integer.parseInt(transferAmount);
                    if (intAmount > 1000) {
                        System.out.println("Maximum amount to transfer is $1000");
                        return;
                    } else if (intAmount % 10 != 0) {
                        System.out.println("Invalid Amount!");
                        return;
                    } else if (balance < intAmount) {
                        System.out.println("Insufficient balance $" + balance);
                        return;
                    }
                    transferFunds(loggedAccount, destinationAccount, intAmount);
                    this.latestFundTransfer = new FundTransfer(destinationAccount, intAmount, referenceNumber);
                    this.inFundTransferSummaryScreen = true;
                } else {
                    System.out.println("Invalid Amount!");
                    return;
                }
            }
            case 2 -> this.inTransactionScreen = true;
        }
        this.inFundTransferScreen = false;
    }

    public void printTransferSummary() {
        System.out.println();
        System.out.println("Fund Transfer Summary");
        System.out.println("Destination com.hafidsb.atm_simulation.models.Account: " + this.latestFundTransfer.getDestinationAccount().getId());
        System.out.println("Transfer Amount: " + this.latestFundTransfer.getTransferAmount());
        System.out.println("Reference Number: " + this.latestFundTransfer.getRefNumber());
        System.out.println("Current Balance: " + this.loggedAccount.getBalance());
    }

    private boolean withdrawFunds(int funds) {
        int balance = this.getLoggedAccount().getBalance();
        if (funds > 1000) {
            System.out.println("Maximum amount to withdraw is $1000");
            return false;
        } else if (balance < funds) {
            System.out.println("Insufficient balance $" + balance);
            return false;
        } else {
            this.getLoggedAccount().setBalance(balance - funds);
            this.latestWithdrawDateTime = LocalDateTime.now();
            this.latestWithdrawAmount = funds;
            return true;
        }
    }

    private void transferFunds(Account source, Account destination, int amount) {
        source.setBalance(source.getBalance() - amount);
        destination.setBalance(destination.getBalance() + amount);
    }

    private boolean invalidLogin(String input, String attribute) {
        if (input.length() != 6) {
            System.out.println(attribute + " should have 6 digits length!\n");
            return true;
        }

        if (!isNumeric(input)) {
            System.out.println(attribute + " should only contains numbers!\n");
            return true;
        }
        return false;
    }

    private boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    private Account findAccount(List<Account> accounts, String accountNumber, String pin) {
        for (Account account : accounts) {
            if (!account.getId().equals(accountNumber)) continue;
            if (!account.getPin().equals(pin)) continue;

            return account;
        }

        return null;
    }

    private Account findAccount(List<Account> accounts, String accountNumber) {
        for (Account account : accounts) {
            if (account.getId().equals(accountNumber)) return account;
        }
        return null;
    }

    private boolean quitTransferFund(String strInput) {
        if (!strInput.isEmpty()) return false;

        this.inFundTransferScreen = false;
        this.inTransactionScreen = true;
        return true;
    }
}
