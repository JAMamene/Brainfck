package level2.command;

import level2.interpreter.BfckContainer;

public class PrintCommand implements Command {

    /**
     * print the memory state of the bfck
     *
     * @param bfck
     */
    public void execute(BfckContainer bfck) {
        System.out.println(bfck);
    }
}
