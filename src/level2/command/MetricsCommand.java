package level2.command;

import level2.constants.Metrics;
import level2.interpreter.Bfck;
import level2.interpreter.BfckMetrics;

public class MetricsCommand implements Command {
    /**
     * print metrics
     * @param bfck
     */
    @Override
    public void execute(Bfck bfck){
        bfck = new BfckMetrics(bfck.getInstructions(),bfck.getFilename(),bfck.getFilenameIn(),bfck.getFilenameOut());
    }
}
