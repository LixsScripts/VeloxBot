package absolute.scripts.moneymaking.planker.utilites;

import org.veloxbot.api.methods.*;
import org.veloxbot.api.methods.Menu;
import org.veloxbot.api.utils.Random;
import org.veloxbot.api.utils.RectUtil;
import org.veloxbot.api.wrappers.DTM;

import java.awt.*;

public class Banks {

    public static void open(final DTM dtm) {
        final Point bankPoint = dtm.getNearest(Game.VIEWPORT_ACTIONBAR_MIN, Game.VIEWPORT_CENTER);
        if (bankPoint != null && Game.isPointValid(bankPoint)) {
            Mouse.move(bankPoint);
            if (OCR.uptextContains("ank")) {
                Mouse.click(true);
                Conditions.bankIsOpen.sleep(Random.nextInt(1200, 2000));
            }  else {
                Camera.setAngle(Random.nextInt(0, 355));
            }
        } else {
            Camera.setAngle(Random.nextInt(0, 355));
        }
    }

    public static void withdrawAll(final DTM dtm) {
        final Point p = dtm.getFirst(Game.VIEWPORT_ACTIONBAR_MAX);
        final Rectangle r = new Rectangle(p.x - 8, p.y - 8, 16, 16);
        Point randomPoint = null;
        while(randomPoint == null)
            randomPoint = RectUtil.getRandomPoint(r);
        Mouse.move(randomPoint);
        if (OCR.uptextContains("ogs")) {
            Mouse.click(false);
            Conditions.menuIsOpen.sleep(Random.nextInt(800, 1200));
            if (Conditions.menuIsOpen.validate()) {
                Menu.clickIndex(5);
                Conditions.containsLogs.sleep(Random.nextInt(1200, 1900));
            }
        } else {
            Camera.setAngle(Random.nextInt(0, 355));
        }
    }
}
