package absolute.scripts.moneymaking.planker.utilites;

import org.veloxbot.api.methods.VPS;

import java.awt.*;

public class Location {

    public static boolean reached(final Point vpsDestination, final int areaSize) {
        final Point playerLocation = VPS.getPosition();
        final int x = vpsDestination.x - areaSize, y = vpsDestination.y - areaSize,
                w = vpsDestination.x + areaSize, h = vpsDestination.y + areaSize;
        final Rectangle area = new Rectangle(x, y, w, h);
        return area.contains(playerLocation);
    }
}
