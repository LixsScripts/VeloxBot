package absolute.scripts.moneymaking.planker.utilites;

import org.veloxbot.api.internals.concurrent.Task;
import org.veloxbot.api.methods.Inventory;
import org.veloxbot.api.net.GEItem;
import org.veloxbot.api.utils.Random;
import org.veloxbot.api.utils.Timer;
import org.veloxbot.api.wrappers.DTM;
import org.veloxbot.api.wrappers.DTMBranch;
import org.veloxbot.api.wrappers.DTMRoot;

import java.awt.*;
import java.util.ArrayList;

public class Variables {

    private static enum ITEMS {
        NORMAL("Normal", DTM.fromString("77_58_34_5/70_52_31_9_-7_5/54_41_24_12_6_5/77_54_24_-14_8_5"), 1511, 100, 960, new Rectangle(146, 131, 74, 64)),
        OAK("Oak", DTM.fromString("103_78_46_5/109_82_48_13_5_5/157_126_88_-9_11_5/157_126_88_-12_-5_5"), 1521, 250, 8778, new Rectangle(294, 133, 80, 65)),
        TEAK("Teak", DTM.fromString("140_117_78_5/105_88_59_10_-6_5/93_78_52_12_7_5/203_171_114_-16_5_5"), 6333, 750, 8780, new Rectangle(141, 258, 81, 62)),
        SPECIAL_TEAK("Special Teak", DTM.fromString("140_117_78_5/105_88_59_10_-6_5/93_78_52_12_7_5/203_171_114_-16_5_5"), 16422, 0, 8780, new Rectangle(299, 259, 75, 61)),
        MAHOGANY("Mahogany", DTM.fromString("134_113_75_5/123_103_69_9_-8_5/101_65_31_-15_1_5/154_129_86_-5_11_5"), 6332, 1500, 8782, new Rectangle(299, 259, 75, 61)),
        SPECIAL_MAHOGANY("Special Mahogany", DTM.fromString("134_113_75_5/123_103_69_9_-8_5/101_65_31_-15_1_5/154_129_86_-5_11_5"), 15290, 0, 8782, new Rectangle(299, 259, 75, 61));

        private String name;
        private DTM dtm;
        private int logId;
        private int makePrice;
        private int plankId;
        private Rectangle bounds;

        private ITEMS(final String paramName, final DTM paramDtm, final int paramLogId, final int paramMakePrice, final int paramPlankId, final Rectangle paramBounds) {
            this.name = paramName;
            this.dtm = paramDtm;
            this.logId = paramLogId;
            this.makePrice = paramMakePrice;
            this.plankId = paramPlankId;
            this.bounds = paramBounds;
        }

        public String getName() {
            return this.name;
        }

        public DTM getDTM() {
            return this.dtm;
        }

        public int getLogID() {
            return this.logId;
        }

        public int getPlankID() {
            return this.plankId;
        }

        public int getMakePrice() {
            return this.makePrice;
        }

        public Rectangle getBounds() {
            return this.bounds;
        }
    }

    public static String state = "";
    public static String logType;
    public final static String MAP_URL_ADDRESS = "http://oi47.tinypic.com/2wr2j5v.jpg";

    public static int planksMade;
    public static int goldMade;
    public static int tripsMade;
    public static int profitPerPlank;

    public static DTM logDTM;
    public final static DTM RUN_DTM = DTM.fromString("171_120_0_5/147_87_0_4_0_5/90_59_4_-4_0_5");
    public final static DTM BANK_DTM = new DTM(new DTMRoot(new Color(55, 53, 53), 4), new DTMBranch(new Color(95, 81, 41), 1, -2, 4));
    public final static DTM SAW_MILL_DTM = new DTM(new DTMRoot(new Color(228, 182, 126), 8), new DTMBranch(new Color(210, 167, 116), 2, 1, 8));

    public static Rectangle logBounds;

    public static boolean madePlanks;

    public final static Point[] POINTS_TO_SAWMILL = {
            new Point(120, 370), new Point(170, 365), new Point(205, 315),
            new Point(215, 260), new Point(240, 215), new Point(260, 160),
            new Point(270, 115)};
    public final static Point[] POINTS_TO_BANK = {
            new Point(260, 175), new Point(230, 225), new Point(220, 280),
            new Point(195, 335), new Point(165, 370), new Point(110, 370),
            new Point(70, 395)};

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static Timer antibanTimer = new Timer(Random.nextInt(15000, 45000));

    private static int getProfitPerPlank(final int logId, final int makePrice, final int plankId) {
        final int logPrice = new GEItem(logId).getGuidePrice();
        final int plankPrice = new GEItem(plankId).getGuidePrice();
        return plankPrice - (makePrice + logPrice);
    }

    public static void getSetUp() {
        for (final ITEMS log : ITEMS.values()) {
            if (Inventory.getCount(log.getDTM()) == 28) {
                state = "Setting: DTMs, Names, Bounds.";
                logDTM = log.getDTM();
                logType = log.getName();
                logBounds = log.getBounds();
                state = "Getting prices of logs, planker, and profit.";
                profitPerPlank = 576 - (250 + 55);//getProfitPerPlank(log.getLogID(), log.getMakePrice(), log.getPlankID());
                break;
            } else if (Inventory.isFull() && Inventory.getCount(log.getDTM()) == 26) {
                //TODO: Add special log plank method
            }
        }
    }
}

