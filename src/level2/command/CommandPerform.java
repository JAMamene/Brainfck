package level2.command;

import level2.interpreter.Bfck;
import level2.reader.BfReader;
import level2.reader.ImageReader;
import level2.reader.InstructionReader;

public class CommandPerform {
    private Bfck bfck;
    private ArgsCheck arg;

    /**
     * process a list of argument to set up the bfck and get the list of the actions
     *
     * @param args
     */

    public CommandPerform(String[] args) {
        arg = new ArgsCheck(args);
        // reader instantiation depends on the given file extension (texte file or image file)
        BfReader reader;
        if ("bmp".equals(arg.getFileExtension())) {
            reader = new ImageReader();
        } else {
            reader = new InstructionReader();
        }
        bfck = new Bfck(reader.ReadFile(arg.getFileName()), arg.getFileName(), arg.getIn(), arg.getOut());
    }

    /**
     * Only for testing purposes
     *
     * @return Bfck the interpreter
     */
    public Bfck getBfck() {
        return bfck;
    }

    public void perform(Command cmd) {
        cmd.execute(bfck);
    }

    /**
     * perform all the action given in argument
     */
    public void performAction() {
        boolean exit = false;
        while (arg.hasActions()) {
            exit = true;
            perform(arg.nextAction());
        }
        if (exit) System.exit(0);
    }

    /**
     * perform all the action possible
     */
    public void performAll() {
        perform(new SetInCommand());
        perform(new SetOutCommand());
        performAction();
        //performing the file interpretation
        perform(new HandleCommand());
        perform(new PrintCommand());
    }
}