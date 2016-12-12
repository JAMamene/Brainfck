package level2.command;

import level2.interpreter.BfckContainer;

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
