package level2.command;


import level2.constants.Languages;
import level2.interpreter.Bfck;
import level2.writer.BfWriter;

public class WriteCodeCommand implements Command {

    private Languages language;

    public WriteCodeCommand(Languages language) {
        this.language = language;
    }

    @Override
    public void execute(Bfck bfck) {
        BfWriter writer = language.getCodeClass();
        writer.WriteFile(bfck.getVisualisableInstructions(), bfck.getFilename());
    }
}
