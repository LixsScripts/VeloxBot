package absolute.scripts.moneymaking.planker.tasks;

import absolute.scripts.moneymaking.planker.utilites.Location;
import absolute.scripts.moneymaking.planker.utilites.Conditions;
import absolute.scripts.moneymaking.planker.utilites.Variables;
import org.veloxbot.api.internals.concurrent.Task;
import org.veloxbot.api.methods.VPS;

import java.awt.*;

public class WalkToBank extends Task {

    @Override
    public boolean validate() {
        return Location.reached(new Point(270, 125), 30) && Conditions.containsNoLogs.validate();
    }

    @Override
    public void run() {
        Variables.state = "Walking to bank...";
        VPS.walkPath(Variables.POINTS_TO_BANK);
    }
}
