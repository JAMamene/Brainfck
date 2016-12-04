package level2.interpreter;


import level2.constants.*;

import java.util.ArrayList;
import java.util.List;

import static level2.constants.InstructionEnum.*;

class Optimizer {

    private List<Executable> instructions;
    private List<Visualisable> optimized;
    private int iter;

    List<Visualisable> optimize(List<Executable> instructions) {
        this.instructions = instructions;
        this.optimized = new ArrayList<>();
        optimized = new ArrayList<>();
        for (iter = 0; iter < instructions.size(); iter++) {
            simplifyIncrDecr();
            simplifyRightLeft();
            simplifySet();
            optimized.add(instructions.get(iter));
        }
        return optimized;
    }

    private Integer simplify(InstructionEnum minus, InstructionEnum plus) {
        if (instructions.get(iter) != minus && instructions.get(iter) != plus) return null;
        Integer value = 0;
        while ((instructions.get(iter) == minus || instructions.get(iter) == plus) && iter + 1 < instructions.size()) {
            if (instructions.get(iter + 1) == plus) {
                System.out.println("plus");
                value += 1;
            } else if (instructions.get(iter + 1) == minus) {
                System.out.println("moins");
                value -= 1;
            } else break;
            ++iter;
        }
        System.out.println(value);
        return value;
    }

    private void simplifyIncrDecr() {
        Integer value = simplify(DECR, INCR);
        if (value != null) {
            optimized.add(new MultiIncrDecr(value));
        }
    }

    private void simplifyRightLeft() {
        Integer value = simplify(LEFT, RIGHT);
        if (value != null) {
            optimized.add(new MultiRightLeft(value));
        }
    }

    private void simplifySet() {
        Integer value = 0;
        if (iter + 2 < instructions.size() && instructions.get(iter) == JUMP && instructions.get(iter + 1) == DECR && instructions.get(iter + 2) == BACK) {
            iter += 3;
            value = simplify(DECR, INCR);
            if (value == null) value = 0;
            optimized.add(new SetCase(value));
        }
    }
}
