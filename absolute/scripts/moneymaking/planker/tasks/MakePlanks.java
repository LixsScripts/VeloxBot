package absolute.scripts.moneymaking.planker.tasks;

import absolute.scripts.moneymaking.planker.utilites.Conditions;
import absolute.scripts.moneymaking.planker.utilites.Variables;
import org.veloxbot.api.internals.concurrent.Task;
import org.veloxbot.api.methods.Menu;
import org.veloxbot.api.methods.Mouse;
import org.veloxbot.api.utils.Random;
import org.veloxbot.api.utils.RectUtil;

public class MakePlanks extends Task {

    @Override
    public boolean validate() {
        return Conditions.interfaceOpen.validate() && Conditions.containsLogs.validate();
    }

    @Override
    public void run() {
        Variables.state = "Making planker.";
        Mouse.click(RectUtil.getRandomPoint(Variables.logBounds), false);
        Conditions.menuIsOpen.sleep(Random.nextInt(800, 1200));
        if (Conditions.menuIsOpen.validate()) {
            Menu.clickIndex(4);
            Conditions.containsNoLogs.sleep(Random.nextInt(1400, 2000));
            if (Conditions.containsNoLogs.validate()) {
                Variables.madePlanks = true;
                Variables.planksMade += 28;
                Variables.goldMade += (28 * Variables.profitPerPlank);
            }
        }
    }
}
