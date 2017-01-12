package level4.command;

import level4.argument.ArgsCheck;
import level4.constants.Metrics;
import level4.interpreter.BfckContainer;
import level4.reader.BfReader;
import level4.reader.ImageReader;
import level4.reader.InstructionReader;

public class CommandPerform {
    private BfckContainer container;
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
        container = new BfckContainer(reader.readFile(arg.getFileName()), arg.getFileName(), arg.getIn(), arg.getOut());
    }

    /**
     * Only for testing purposes
     *
     * @return Bfck the interpreter
     */
    public BfckContainer getContainer() {
        return container;
    }

    private void perform(Command cmd) {
        cmd.execute(container);
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

    private void performPassiveAction() {
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