package level4.command;

import level4.interpreter.BfckContainer;

public class TranslateCommand implements Command {
    /**
     * print the list of image in image syntax
     * the file containing the image will have the same main as the file containing the instruction
     *
     * @param bfck
     */
    @Override
    public void execute(BfckContainer bfck) {
        bfck.translate();
    }
}
