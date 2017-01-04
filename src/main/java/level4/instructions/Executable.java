package level4.instructions;

import level4.interpreter.Interpreter;
import level4.interpreter.Memory;

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
