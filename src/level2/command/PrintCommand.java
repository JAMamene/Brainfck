package level2.command;

import level2.interpreter.Bfck;

public class PrintCommand implements Command {

    /**
     * print the memory state of the bfck
     *
     * @param bfck
     */
    public void execute(Bfck bfck) {
        System.out.println(bfck);
    }
}
