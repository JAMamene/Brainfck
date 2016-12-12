package level2.command;

import level2.interpreter.BfckContainer;
import level2.writer.BfWriter;
import level2.writer.InstructionWriter;

public class RewriteCommand implements Command {
    private BfWriter instructWriter = new InstructionWriter();

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
