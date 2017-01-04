package level4.command;

import level4.interpreter.BfckContainer;

public interface Command {
    /**
     * execute methode, apply the action to the bfck provided
     */
    void execute(BfckContainer bfck);
}
