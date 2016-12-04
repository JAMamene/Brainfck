package level2.constants;

import java.awt.*;

public class MultiRightLeft implements Visualisable {

    private int value;

    public MultiRightLeft(int value) {
        this.value = value;
    }

    @Override
    public String getJava() {
        if (value == 0) {
            return null;
        } else if (value == -1) {
            return "++i;";
        } else if (value == 1) {
            return "--i;";
        } else if (value > 1) {
            return "i+=" + value + ";";
        } else {
            return "i-=" + Math.abs(value) + ";";
        }
    }

    @Override
    public String getC() {
        if (value == 0) {
            return null;
        } else if (value == -1) {
            return "++mem;";
        } else if (value == 1) {
            return "--mem;";
        } else if (value > 1) {
            return "mem+=" + value + ";";
        } else {
            return "mem-=" + Math.abs(value) + ";";
        }
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public char getShortcut() {
        return 0;
    }
}
