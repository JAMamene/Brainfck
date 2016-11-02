package level2.command;

import level2.interpreter.Bfck;

public class HandleCommand implements Command {

    /**
     * execute all the instruction in the list of the bfck
     *
     * @param bfck
     */

    @Override
    public void execute(Bfck bfck) {
        bfck.handle();
    }
}
