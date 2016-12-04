package level2.constants;

import java.awt.*;

public class SetCase implements Visualisable {

    private int value;

    public SetCase(int value) {
        this.value = value;
    }

    @Override
    public String getJava() {
        if (value < 0) {
            return null;
        } else if (value == 0) {
            return "mem[i] = 0;";
        } else {
            return "mem[i] = " + value + ";";
        }
    }

    @Override
    public String getC() {
        if (value < 0) {
            return null;
        } else if (value == 0) {
            return "*mem = 0;";
        } else {
            return "*mem = " + value + ";";
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
