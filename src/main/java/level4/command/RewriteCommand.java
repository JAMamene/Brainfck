package level4.command;

import level4.interpreter.BfckContainer;

public class RewriteCommand implements Command {
    /**
     * print the instructions of the bfck in short syntax on standard output
     *
     * @param bfck
     */
    @Override
    public void execute(BfckContainer bfck) {
        bfck.rewrite();
    }
}
