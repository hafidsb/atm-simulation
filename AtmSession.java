import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Getter @Setter
public class AtmSession {
    private Account loggedAccount;
    private boolean running;
    private boolean inWelcomeScreen;
    private boolean inTransactionScreen;
    private boolean inWithdrawScreen;
    private boolean inOtherWithdrawScreen;
    private boolean inSummaryScreen;
    private Scanner sessionScanner;
    private int latestWithdrawAmount;
    private LocalDateTime latestWithdrawDateTime;

    public AtmSession() {
        this.running = true;
        this.inWelcomeScreen = true;
        this.inTransactionScreen = false;
        this.inWithdrawScreen = false;
        this.inOtherWithdrawScreen = false;
        this.inSummaryScreen = false;
        this.sessionScanner = new Scanner(System.in);
    }

    public void processLogin(List<Account> registeredAccounts) {
        System.out.print("Enter Account Number: ");
        String accountNumber = sessionScanner.nextLine();
        if (invalidLogin(accountNumber, "Account Number")) return;

        System.out.print("Enter PIN: ");
        String pin = sessionScanner.nextLine();
        if (invalidLogin(pin, "PIN")) return;

        this.loggedAccount = findAccount(registeredAccounts, accountNumber, pin);
        if (this.loggedAccount == null) {
            System.out.println("Invalid Account Number/PIN!\n");
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
            case 2 -> System.out.println("2");
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
                this.withdrawFunds(10);
                this.inSummaryScreen = true;
            }
            case 2 -> {
                this.withdrawFunds(50);
                this.inSummaryScreen = true;
            }
            case 3 -> {
                this.withdrawFunds(100);
                this.inSummaryScreen = true;
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

        this.withdrawFunds(funds);
        this.inSummaryScreen = true;
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
            case 1 -> {
                this.withdrawFunds(10);
                this.inSummaryScreen = true;
            }
            case 2 -> {
                this.withdrawFunds(50);
                this.inSummaryScreen = true;
            }
        }
        this.inSummaryScreen = false;
    }

    public void printWithdrawSummary() {
        System.out.println("Summary");
        System.out.println("Date: " + latestWithdrawDateTime);
        System.out.println("Withdraw: " + latestWithdrawAmount);
        System.out.println("Balance: " + this.loggedAccount.getBalance());
    }

    private void withdrawFunds(int funds) {
        int balance = this.getLoggedAccount().getBalance();
        if (funds > 1000) {
            System.out.println("Maximum amount to withdraw is $1000");
        } else if (balance < funds) {
            System.out.println("Insufficient balance $" + balance);
        } else {
            this.getLoggedAccount().setBalance(balance - funds);
            this.latestWithdrawDateTime = LocalDateTime.now();
            this.latestWithdrawAmount = funds;
        }
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
}
