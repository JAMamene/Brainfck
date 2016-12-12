package level2.command;


import level2.constants.Languages;
import level2.interpreter.BfckContainer;
import level2.writer.BfWriter;

public class WriteCodeCommand implements Command {

    private Languages language;
    private Boolean optimize;

    public WriteCodeCommand(Languages language, Boolean optimize) {
        this.language = language;
        this.optimize = optimize;
    }

    @Override
    public void execute(BfckContainer bfck) {
        bfck.writeCode(language, optimize);
    }
}
