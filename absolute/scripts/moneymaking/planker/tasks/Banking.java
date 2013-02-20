package absolute.scripts.moneymaking.planker.tasks;

import absolute.scripts.moneymaking.planker.utilites.Location;
import absolute.scripts.moneymaking.planker.utilites.Banks;
import absolute.scripts.moneymaking.planker.utilites.Conditions;
import absolute.scripts.moneymaking.planker.utilites.Variables;
import org.veloxbot.api.internals.concurrent.Task;
import org.veloxbot.api.methods.Bank;
import org.veloxbot.api.methods.Inventory;

import java.awt.*;

public class Banking extends Task {

    @Override
    public boolean validate() {
        return Location.reached(new Point(70, 365), 15) && !Conditions.containsLogs.validate();
    }

    @Override
    public void run() {
        if (Conditions.bankIsOpen.validate()) {
            if (!Inventory.isEmpty()) {
                Variables.state = "Depositing planker.";
                Bank.depositAll();
                if (Variables.madePlanks)
                    Variables.tripsMade++;
                Variables.madePlanks = false;
            }
            Variables.state = "Withdrawing " + Variables.logType.toLowerCase() + " logs.";
            Banks.withdrawAll(Variables.logDTM);
        } else {
            Variables.state = "Opening bank.";
            Banks.open(Variables.BANK_DTM);
        }
    }
}
