import java.util.*;

public class AtmSimulation {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        List<Account> registeredAccounts = generateAccounts();

        boolean appIsRunning = true;
        boolean inWelcomeScreen = true;
        int input = 0;

        while (appIsRunning) {
            System.out.println("Welcome to the ATM Simulation!");

            while (inWelcomeScreen) {
                System.out.print("Enter Account Number: ");
                String accountNumber = myScanner.nextLine();
                if (invalidLogin(accountNumber)) continue;

                System.out.print("Enter PIN: ");
                String pin = myScanner.nextLine();
                if (invalidLogin(pin)) continue;

                if (!accountFound(registeredAccounts, accountNumber, pin)) {
                    System.out.println("Invalid Account Number/PIN!\n");
                    continue;
                }
                inWelcomeScreen = false;
            }

            printMenu();
            appIsRunning = false;
        }
    }

    static void printMenu() {
        System.out.println("Login Success");
    }

    static List<Account> generateAccounts() {
        Account account1 = new Account("112233", "John Doe", "012118", 100);
        Account account2 = new Account("112244", "jane Doe", "932012", 30);
        return Arrays.asList(account1, account2);
    }

    static boolean invalidLogin(String input) {
        if (input.length() != 6) {
            System.out.println("Input should have 6 digits length!\n");
            return true;
        }

        if (!isNumeric(input)) {
            System.out.println("Input should only contains numbers!\n");
            return true;
        }
        return false;
    }

    static boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    static boolean accountFound(List<Account> accounts, String accountNumber, String pin) {
        boolean found = false;
        for (Account account : accounts) {
            if (!account.getId().equals(accountNumber)) continue;
            if (!account.getPin().equals(pin)) continue;

            found = true;
            break;
        }

        return found;
    }
}
