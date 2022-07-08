package com.hafidsb.atm_simulation.states;

import java.util.Scanner;

public abstract class BaseState {
    protected final Scanner scanner;

    protected BaseState() {
        this.scanner = new Scanner(System.in);
    }
}
