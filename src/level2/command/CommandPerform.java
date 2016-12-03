package level2.command;

import level2.argument.ArgsCheck;
import level2.constants.Metrics;
import level2.interpreter.Bfck;
import level2.interpreter.BfckMetrics;
import level2.reader.BfReader;
import level2.reader.ImageReader;
import level2.reader.InstructionReader;

public class CommandPerform {
    private Bfck bfck;
    private ArgsCheck arg;

    /**
     * process a list of argument to set up the bfck and get the list of the actions
     *
     * @param args program's args
     */

    public CommandPerform(String[] args) {
        Metrics.beginExecTime();
        arg = new ArgsCheck(args);
        // reader instantiation depends on the given file extension (texte file or image file)
        BfReader reader;
        if ("bmp".equals(arg.getFileExtension())) {
            reader = new ImageReader();
        } else {
            reader = new InstructionReader();
        }
        if(!arg.getMetrics())bfck = new Bfck(reader.readFile(arg.getFileName()), arg.getFileName(), arg.getIn(), arg.getOut());
        else bfck = new BfckMetrics(reader.readFile(arg.getFileName()), arg.getFileName(), arg.getIn(), arg.getOut());
    }

    /**
     * Only for testing purposes
     *
     * @return Bfck the interpreter
     */
    public Bfck getBfck() {
        return bfck;
    }

    private void perform(Command cmd) {
        cmd.execute(bfck);
    }

    /**
     * perform all the action given in argument
     */
    public void performStoppingAction() {
        boolean exit = false;
        while (arg.hasStoppingActions()) {
            exit = true;
            perform(arg.nextStoppingAction());
        }
        if (exit) System.exit(0);
    }

    public void performPassiveAction() {
        while (arg.hasPassiveActions()) {
            perform(arg.nextPassiveAction());
        }
    }

    /**
     * perform all the action possible
     */
    public void performAll() {
        perform(new SetInCommand());
        perform(new SetOutCommand());

        performStoppingAction();

        performPassiveAction();

        //performing the file interpretation
        perform(new HandleCommand());
        Metrics.endExecTime();
        perform(new PrintCommand());
    }
}