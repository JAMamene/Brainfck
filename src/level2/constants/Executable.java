package level2.constants;

import level2.interpreter.Bfck;
import level2.interpreter.Interpreter;

import java.awt.*;


public interface Executable extends Visualisable {

    void exec(Bfck bfck, Interpreter interpreter);

    Color getColor();

    char getShortcut();
}
