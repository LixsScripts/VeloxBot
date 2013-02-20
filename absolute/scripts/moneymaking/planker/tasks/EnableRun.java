package absolute.scripts.moneymaking.planker.tasks;

import absolute.scripts.moneymaking.planker.utilites.Conditions;
import absolute.scripts.moneymaking.planker.utilites.Variables;
import org.veloxbot.api.internals.concurrent.Task;
import org.veloxbot.api.methods.Mouse;
import org.veloxbot.api.methods.Player;
import org.veloxbot.api.utils.Random;
import org.veloxbot.api.utils.RectUtil;

public class EnableRun extends Task {

    @Override
    public boolean validate() {
        return !Conditions.isRunEnabled.validate() && Player.getEnergy() > Random.nextInt(30, 50);
    }

    @Override
    public void run() {
        Variables.state = "Setting run.";
        Mouse.click(RectUtil.getRandomPoint(Player.RUN_RECTANGLE), true);
    }
}
