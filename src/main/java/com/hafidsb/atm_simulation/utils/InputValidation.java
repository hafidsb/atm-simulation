package com.hafidsb.atm_simulation.utils;

public class InputValidation {
    private InputValidation() {}

    public static boolean isNumeric(String input) {
        return input.chars().allMatch(Character::isDigit);
    }

    public static boolean validateLogin(String input, String attribute) {
        if (input.length() != 6) {
            System.out.println(attribute + " should have 6 digits length!\n");
            return false;
        }

        if (!isNumeric(input)) {
            System.out.println(attribute + " should only contains numbers!\n");
            return false;
        }

        return true;
    }

    public static boolean validateWithdrawFunds(int amount, int balance) {
        if (amount > 1000) {
            System.out.println("Maximum amount to withdraw is $1000");
            return false;
        } else if (amount > balance) {
            System.out.println("Insufficient balance $" + balance);
            return false;
        }

        return true;
    }
}
