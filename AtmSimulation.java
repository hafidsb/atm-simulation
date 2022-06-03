import java.util.*;

public class AtmSimulation {
    public static void main(String[] args) {
        AtmSession session = new AtmSession();
        List<Account> registeredAccounts = generateAccounts();

        while (session.isRunning()) {
            System.out.println("Welcome to the ATM Simulation!");

            while (session.isInWelcomeScreen()) {
                session.processLogin(registeredAccounts);
            }

            while (session.isInTransactionScreen()) {
                transactionScreen();
                session.processTransaction();
            }

            while (session.isInWithdrawScreen()) {
                withdrawScreen();
                session.processWithdraw();
            }

            while (session.isInOtherWithdrawScreen()) {
                otherWithdrawScreen();
                session.processOtherWithdraw();
            }

            while (session.isInSummaryScreen()) {
                session.printWithdrawSummary();
                summaryScreen();

            }

            session.setRunning(false);
        }
    }

    static List<Account> generateAccounts() {
        Account account1 = new Account("112233", "John Doe", "012118", 100);
        Account account2 = new Account("112244", "jane Doe", "932012", 30);
        return Arrays.asList(account1, account2);
    }

    static void transactionScreen() {
        System.out.println("Login Success");
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");
        System.out.print("Please choose an option[default is 3]: ");
    }

    static void withdrawScreen() {
        System.out.println("Withdraw Funds");
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");
        System.out.print("Please choose an option[default is 5]: ");
    }

    static void otherWithdrawScreen() {
        System.out.println("Other Withdraw");
        System.out.print("Please enter amount to withdraw:: ");
    }

    static void summaryScreen() {
        System.out.println("");
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Please choose an option[default is 2]: ");
    }
}
