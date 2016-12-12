package level2.command;

import level2.interpreter.BfckContainer;

public class MetricsCommand implements Command {
    /**
     * print metrics
     * @param bfck
     */
    @Override
    public void execute(BfckContainer bfck){
        bfck.toMetrics();
    }
}
