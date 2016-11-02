package level2.command;

import level2.interpreter.Bfck;

public interface Command {
    /**
     * execute methode, apply the action to the bfck provided
     *
     * @param bfck
     */
    void execute(Bfck bfck);
}
