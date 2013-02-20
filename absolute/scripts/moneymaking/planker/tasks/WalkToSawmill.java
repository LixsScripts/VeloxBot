package absolute.scripts.moneymaking.planker.tasks;

import absolute.scripts.moneymaking.planker.utilites.Location;
import absolute.scripts.moneymaking.planker.utilites.Conditions;
import absolute.scripts.moneymaking.planker.utilites.Variables;
import org.veloxbot.api.internals.concurrent.Task;
import org.veloxbot.api.methods.VPS;

import java.awt.*;

public class WalkToSawmill extends Task {

    @Override
    public boolean validate() {
        return !Location.reached(new Point(265, 120), 20) && Conditions.containsLogs.validate();
    }

    @Override
    public void run() {
        Variables.state = "Walking to sawmill...";
        VPS.walkPath(Variables.POINTS_TO_SAWMILL);
    }
}
