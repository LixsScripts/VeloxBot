package absolute.scripts.moneymaking.planker.utilites;

import org.veloxbot.api.methods.Bank;
import org.veloxbot.api.methods.Inventory;
import org.veloxbot.api.methods.Menu;
import org.veloxbot.api.wrappers.Condition;
import org.veloxbot.api.wrappers.DTM;

import java.awt.*;

public class Conditions {

    public static Condition bankIsOpen = new Condition() {
        @Override
        public boolean validate() {
            return Bank.isOpen();
        }
    };
    public static Condition menuIsOpen = new Condition() {
        @Override
        public boolean validate() {
            return Menu.isOpen();
        }
    };
    public static Condition containsLogs = new Condition() {
        @Override
        public boolean validate() {
            return Inventory.getCount(Variables.logDTM) > 0;
        }
    };
    public static Condition containsNoLogs = new Condition() {
        @Override
        public boolean validate() {
            return Inventory.getCount(Variables.logDTM) == 0;
        }
    };
    public static Condition interfaceOpen = new Condition() {
        @Override
        public boolean validate() {
            final DTM dtm = DTM.fromString("255_152_31_5/255_152_31_-2_5_5/255_152_31_1_5_5/255_152_31_-2_-4_5");
            return dtm.getFirst(new Rectangle(104, 69, 41, 33)) != null;
        }
    };
    public static Condition isRunEnabled = new Condition() {
        @Override
        public boolean validate() {
            return Variables.RUN_DTM.getFirst(new Rectangle(705, 145, 30, 30)) != null;
        }
    };
}
