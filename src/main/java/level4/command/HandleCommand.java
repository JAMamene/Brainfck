package level4.command;

import level4.interpreter.BfckContainer;

public class HandleCommand implements Command {

    /**
     * execute all the instruction in the list of the bfck
     *
     * @param bfck
     */

    @Override
    public void execute(BfckContainer bfck) {
        bfck.handle();
    }
}
