package level2.command;


import level2.constants.Languages;
import level2.interpreter.Bfck;
import level2.writer.BfWriter;

public class WriteCodeCommand implements Command {

    private Languages language;
    private Boolean optimize;

    public WriteCodeCommand(Languages language, Boolean optimize) {
        this.language = language;
        this.optimize = optimize;
    }

    @Override
    public void execute(Bfck bfck) {
        BfWriter writer = language.getCodeClass();
        if (optimize) {
            writer.WriteFile(bfck.getOptimizedInstructions(), bfck.getFilename());
        } else {
            writer.WriteFile(bfck.getVisualisableInstructions(), bfck.getFilename());
        }
    }
}
