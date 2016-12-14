package level2.constants;

import level2.interpreter.Interpreter;
import level2.interpreter.Memory;

import java.awt.*;


public interface Executable {

    void exec(Memory bfck, Interpreter interpreter);

}
