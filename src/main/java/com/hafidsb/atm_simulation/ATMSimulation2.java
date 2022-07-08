package com.hafidsb.atm_simulation;

import com.hafidsb.atm_simulation.models.ATMSession;
import com.hafidsb.atm_simulation.states.TransactionScreen;
import com.hafidsb.atm_simulation.states.WelcomeScreen;
import com.hafidsb.atm_simulation.states.WithdrawScreen;


public class ATMSimulation2 {
    public static void main(String[] args) {
        ATMSession newSession = new ATMSession();
        boolean running = true;

        while (running) {
            switch (newSession.getStateEnum()) {
                case WELCOME -> newSession.setStateObject(new WelcomeScreen());
                case TRANSACTION -> newSession.setStateObject(new TransactionScreen());
                case WITHDRAW -> newSession.setStateObject(new WithdrawScreen());
                case OTHER_WITHDRAW -> System.out.println("Other withdraw");
                case SUMMARY -> System.out.println("Summary");
                default -> {
                    System.out.println("EXIT");
                    running = false;
                }
            }
            newSession.executeStateProcess();
        }
    }
}
