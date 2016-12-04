package level2.command;

import level2.interpreter.Bfck;
import level2.writer.BfWriter;
import level2.writer.ImageWriter;

public class TranslateCommand implements Command {
    private BfWriter imgWriter = new ImageWriter();

    /**
     * print the list of image in image syntax
     * the file containing the image will have the same main as the file containing the instruction
     *
     * @param bfck
     */
    @Override
    public void execute(Bfck bfck) {
        imgWriter.WriteFile(bfck.getVisualisableInstructions(), bfck.getFilename());
    }
}
