package level2.constants;

import level2.interpreter.Bfck;

import java.awt.*;


public interface Executable {

    void exec(Bfck bfck);

    Color getColor();

    char getShortcut();
}
