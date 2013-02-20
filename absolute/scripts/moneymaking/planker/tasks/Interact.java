package absolute.scripts.moneymaking.planker.tasks;

import absolute.scripts.moneymaking.planker.utilites.Location;
import absolute.scripts.moneymaking.planker.utilites.Conditions;
import absolute.scripts.moneymaking.planker.utilites.Variables;
import org.veloxbot.api.internals.concurrent.Task;
import org.veloxbot.api.methods.*;
import org.veloxbot.api.methods.Menu;
import org.veloxbot.api.utils.Random;

import java.awt.*;

public class Interact extends Task {

    @Override
    public boolean validate() {
        return Location.reached(new Point(270, 115), 20)
                && Conditions.containsLogs.validate() && !Conditions.interfaceOpen.validate();
    }

    @Override
    public void run() {
        Variables.state = "Interacting.";
        final Point sawmillPoint = Variables.SAW_MILL_DTM.getNearest(Game.VIEWPORT_ACTIONBAR_MIN, Game.VIEWPORT_CENTER);
        if (sawmillPoint != null && Game.isPointValid(sawmillPoint)) {
            Mouse.move(sawmillPoint);
            if (OCR.uptextContains("Talk")) {
                Mouse.click(false);
                Conditions.menuIsOpen.sleep(Random.nextInt(800, 1100));
                if (Conditions.menuIsOpen.validate()) {
                    Menu.clickIndex(1);
                    Conditions.interfaceOpen.sleep(Random.nextInt(3000, 5500));
                }
            } else {
                Camera.setAngle(Random.nextInt(0, 355));
            }
        } else {
            Camera.setAngle(Random.nextInt(0, 355));
        }
    }
}
