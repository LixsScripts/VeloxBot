package absolute.scripts.moneymaking.planker;

import absolute.scripts.moneymaking.planker.tasks.*;
import absolute.scripts.moneymaking.planker.utilites.Variables;
import org.veloxbot.api.ActiveScript;
import org.veloxbot.api.Manifest;
import org.veloxbot.api.internals.concurrent.Task;
import org.veloxbot.api.methods.Game;
import org.veloxbot.api.methods.Mouse;
import org.veloxbot.api.methods.VPS;
import org.veloxbot.api.utils.Random;

import java.awt.*;

@Manifest(authors = {"Pixel"}, version = 1.1D, name = "Absolute Planker", description = "Plank making script at Varrock sawmill.")
public class AbsolutePlanker extends ActiveScript {

    private final AntiBan antiban = new AntiBan();
    private final EnableRun enableRun = new EnableRun();

    @Override
    public boolean onStart() {
        Mouse.setVisible(false);
        VPS.setMapData(VPS.getOnlineMapData(Variables.MAP_URL_ADDRESS));
        Variables.getSetUp();
        Variables.tasks.add(new WalkToBank());
        Variables.tasks.add(new Banking());
        Variables.tasks.add(new WalkToSawmill());
        Variables.tasks.add(new Interact());
        Variables.tasks.add(new MakePlanks());
        return true;
    }

    @Override
    public long loop() {
        Mouse.setMultiplier(Random.nextDouble(0.5, 0.8));
        if (!enableRun.isAlive() && enableRun.validate()) {
            enableRun.run();
        }
        for (final Task task : Variables.tasks)
            if (!task.isAlive() && task.validate())
                task.run();
        if (!antiban.isAlive() && antiban.validate())
            antiban.run();
        return 0;
    }

    private final RenderingHints antialiasing = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    private final Color backgroundColor = new Color(0, 0, 0, 230);
    private final Color borderColor = new Color(255, 102, 255, 180);
    private final Color titleColor = new Color(255, 102, 255, 180);
    private final Color statisticColor = new Color(153, 204, 255, 180);
    private final Color crossColor = new Color(255, 255, 255, 190);

    private final BasicStroke stroke1 = new BasicStroke(1);
    private final BasicStroke stroke2 = new BasicStroke(0);

    private final Font font1 = new Font("Lucida Handwriting", Font.TRUETYPE_FONT, 25);
    private final Font font2 = new Font("Lucida Handwriting", Font.TRUETYPE_FONT, 14);
    private final Font font3 = new Font("Lucida Handwriting", Font.TRUETYPE_FONT, 12);
    private final Font font4 = new Font("Lucida Handwriting", Font.TRUETYPE_FONT, 10);

    @Override
    public void onRepaint(final Graphics g1) {
        final Graphics2D g = (Graphics2D)g1;
        g.setRenderingHints(antialiasing);
        g.setColor(backgroundColor);
        g.fillRect(6, 394, 507, 35);
        g.fillRect(6, 507, 507, 16);
        g.fillRect(6, 394, 507, 128);
        g.setColor(borderColor);
        g.setStroke(stroke1);
        g.drawRect(6, 394, 505, 128);
        g.setFont(font1);
        g.setColor(titleColor);
        g.drawString("Absolute Planker", 7, 420);
        g.setFont(font2);
        g.setColor(statisticColor);
        g.drawString("Time running: " + runTimer.toElapsedString(), 30, 450);
        g.drawString("State: " + Variables.state, 260, 450);
        g.drawString("Log type: " + Variables.logType , 260, 470);
        g.drawString("Trips: " + Variables.tripsMade + "(" + Math.round(runTimer.getHourlyCalculation(Variables.tripsMade)) + ")", 30, 470);
        g.drawString("Planks: " + Variables.planksMade + "(" + Math.round(runTimer.getHourlyCalculation(Variables.planksMade)) + ")", 30, 490);
        g.drawString("Profit: " + Variables.goldMade + "(" + Math.round(runTimer.getHourlyCalculation(Variables.goldMade)) + ")", 261, 490);
        g.setFont(font3);
        g.drawString("version 1.1", 10, 520);
        g.setFont(font4);
        g.drawString("by Pixel", 460, 520);
        g.setFont(font2);
        g.setStroke(stroke2);
        g.setColor(crossColor);
        final Point m = Mouse.getLocation();
        g.drawLine(Game.BOUNDS.x, m.y, Game.BOUNDS.width, m.y);
        g.drawLine(m.x, Game.BOUNDS.y, m.x, Game.BOUNDS.height);
    }
}
