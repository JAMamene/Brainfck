package level2.command;

import level2.interpreter.Bfck;

public class CheckCommand implements Command {

    /**
     * check if the list of instruction in the bfck is correct
     * if the instructions are corrects exit silently
     *
     * @param bfck
     */

    @Override
    public void execute(Bfck bfck) {
        bfck.check();
    }
}
