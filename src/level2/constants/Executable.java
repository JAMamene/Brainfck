package level2.constants;

import level2.interpreter.Interpreter;
import level2.interpreter.Memory;

import java.awt.*;

/**
 * Interface for the executable objects, thoses who will be send to the interpreter
 * mostly instructionenum or procedure/function
 */
public interface Executable {

    /**
     * The action the object does on the memory
     *
     * @param bfck        the memory
     * @param interpreter the interpreter
     */
    void exec(Memory bfck, Interpreter interpreter);

}
