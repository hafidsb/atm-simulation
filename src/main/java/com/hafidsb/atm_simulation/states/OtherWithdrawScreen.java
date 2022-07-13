package com.hafidsb.atm_simulation.states;

import com.hafidsb.atm_simulation.enums.ATMStateEnum;
import com.hafidsb.atm_simulation.models.ATMSession;
import com.hafidsb.atm_simulation.models.Account;

import java.util.List;

import static com.hafidsb.atm_simulation.enums.ATMStateEnum.OTHER_WITHDRAW;
import static com.hafidsb.atm_simulation.enums.ATMStateEnum.SUMMARY;
import static com.hafidsb.atm_simulation.utils.TransactionUtil.canWithdrawFunds;
import static com.hafidsb.atm_simulation.utils.TransactionUtil.withdrawFunds;
import static java.lang.Integer.parseInt;

public class OtherWithdrawScreen extends BaseState implements IState {
    @Override
    public void printInitialMessage(ATMSession session) {
        System.out.println();
        System.out.println("Other Withdraw");
        System.out.print("Please enter amount to withdraw: ");
    }

    @Override
    public ATMStateEnum process(List<Account> accounts, ATMSession session) {
        int amount = 0;
        String strAmount = scanner.nextLine();
        try {
            amount = parseInt(strAmount);
            if (amount % 10 != 0) {
                System.out.println("Invalid amount!");
                return OTHER_WITHDRAW;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid amount!");
            if (!strAmount.isEmpty()) return OTHER_WITHDRAW;
        }

        if (canWithdrawFunds(session, amount)) {
            withdrawFunds(session, amount);
            return SUMMARY;
        } else {
            return OTHER_WITHDRAW;
        }
    }
}