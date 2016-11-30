package level2.command;

import level2.constants.Metrics;
import level2.interpreter.Bfck;

public class PrintMetricsCommand implements Command {

    @Override
    public void execute(Bfck bfck) {
        Metrics.print();
    }
}
