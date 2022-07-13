package com.hafidsb.atm_simulation;

import com.hafidsb.atm_simulation.models.ATMSession;
import com.hafidsb.atm_simulation.states.*;


public class ATMSimulation {
    public static void main(String[] args) {
        ATMSession newSession = new ATMSession();
        boolean running = true;

        while (running) {
            switch (newSession.getStateEnum()) {
                case WELCOME -> newSession.setStateObject(new WelcomeScreen());
                case TRANSACTION -> newSession.setStateObject(new TransactionScreen());
                case WITHDRAW -> newSession.setStateObject(new WithdrawScreen());
                case OTHER_WITHDRAW -> newSession.setStateObject(new OtherWithdrawScreen());
                case SUMMARY -> newSession.setStateObject(new SummaryScreen());
                case FUND_TRANSFER -> newSession.setStateObject(new FundTransferScreen());
                case FUND_TRANSFER_SUMMARY -> newSession.setStateObject(new FundTransferSummaryScreen());
                default -> {
                    System.out.println("EXIT");
                    running = false;
                }
            }
            newSession.executeStateProcess();
        }
    }
}
