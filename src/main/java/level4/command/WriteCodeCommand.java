package level4.command;


import level4.constants.Languages;
import level4.interpreter.BfckContainer;

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
