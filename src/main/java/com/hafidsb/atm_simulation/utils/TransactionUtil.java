package com.hafidsb.atm_simulation.utils;

import com.hafidsb.atm_simulation.models.ATMSession;
import com.hafidsb.atm_simulation.models.Account;
import com.hafidsb.atm_simulation.models.TransferTransaction;
import com.hafidsb.atm_simulation.models.WithdrawTransaction;

import java.time.LocalDateTime;
import java.util.Random;

import static com.hafidsb.atm_simulation.utils.InputValidation.validateWithdrawFunds;

public class TransactionUtil {
    private TransactionUtil() {}

    private static final Random random = new Random();

    public static String generateRefNumber() {
        return String.format("%06d", random.nextInt(1_000_000));
    }

    public static boolean canWithdrawFunds(ATMSession session, int amount) {
        int balance = session.getLoggedAccount().getBalance();
        return validateWithdrawFunds(amount, balance);
    }

    public static void withdrawFunds(ATMSession session, int amount) {
        var transaction = new WithdrawTransaction(
                generateRefNumber(),
                amount,
                LocalDateTime.now(),
                session.getLoggedAccount().getId()
        );

        session.setLatestTransaction(transaction);
        session.getAtm().addWithdraw(transaction);
        session.getLoggedAccount().reduceBalance(amount);
    }

    public static boolean canTransferFunds(ATMSession session, int amount) {
        int balance = session.getLoggedAccount().getBalance();

        if (amount > 1000) {
            System.out.println("Maximum amount to transfer is $1000");
            return false;
        } else if (amount % 10 != 0) {
            System.out.println("Invalid Amount!");
            return false;
        } else if (balance < amount) {
            System.out.println("Insufficient balance $" + balance);
            return false;
        }

        return true;
    }

    public static void transferFunds(ATMSession session, int amount, Account destination) {
        var transaction = new TransferTransaction(
                generateRefNumber(),
                amount,
                LocalDateTime.now(),
                session.getLoggedAccount().getId(),
                destination.getId()
        );

        session.setLatestTransaction(transaction);
        session.getAtm().addTransfer(transaction);
        session.getLoggedAccount().reduceBalance(amount);
        destination.addBalance(amount);
    }
}
