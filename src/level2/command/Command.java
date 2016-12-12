package level2.command;

import level2.interpreter.BfckContainer;

public interface Command {
    /**
     * execute methode, apply the action to the bfck provided
     *
     *
     */
    void execute(BfckContainer bfck);
}
