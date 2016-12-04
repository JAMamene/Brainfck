package level2.interpreter;


import level2.constants.Executable;
import level2.constants.Visualisable;

import java.util.ArrayList;
import java.util.List;

public class Optimizer {

    public List<Visualisable> optimize(List<Executable> instructions) {
        List<Visualisable> optimized = new ArrayList<>();
        for (Executable e : instructions) {
            optimized.add(e);
        }
        return optimized;
    }
}
