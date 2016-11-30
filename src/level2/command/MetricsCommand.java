package level2.command;

import level2.constants.Metrics;
import level2.interpreter.Bfck;

public class MetricsCommand implements Command {
    /**
     * print metrics
     * @param bfck
     */
    @Override
    public void execute(Bfck bfck){
        Metrics.turnOn();
    }
}
