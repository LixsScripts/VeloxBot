package absolute.scripts.moneymaking.planker.tasks;

import absolute.scripts.moneymaking.planker.utilites.Variables;
import org.veloxbot.api.internals.concurrent.Task;
import org.veloxbot.api.methods.Camera;
import org.veloxbot.api.methods.Game;
import org.veloxbot.api.methods.Mouse;
import org.veloxbot.api.utils.Random;
import org.veloxbot.api.utils.RectUtil;
import org.veloxbot.api.utils.Timer;

import java.awt.*;

public class AntiBan extends Task {

    @Override
    public boolean validate() {
        return !Variables.antibanTimer.isRunning();
    }

    @Override
    public void run() {
        Variables.state = "Antiban.";
        if (Random.nextInt(0, 11) % 2 == 0) {
            Point p = RectUtil.getRandomPoint(Game.BOUNDS);
            if (p != null)
                Mouse.move(p);
        } else {
            Camera.setAngle(Random.nextInt(0, 355));
        }
        Variables.antibanTimer = new Timer(Random.nextInt(15000, 45000));
    }
}
