package level4.interpreter;


import level4.Optimization.MultiIncrDecr;
import level4.Optimization.MultiRightLeft;
import level4.Optimization.SetCell;
import level4.instructions.InstructionEnum;
import level4.instructions.Visualisable;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import static level4.instructions.InstructionEnum.*;

/**
 * A parser class to optimize a set of visualisable into more compact instructions
 */
public class Optimizer {

    private List<Visualisable> instructions;
    private List<Visualisable> optimized;
    private int iter;

    /**
     * @param instructions the list of visualisable to optimize
     * @return the new shorter or equal optimized list
     */
    public List<Visualisable> optimize(List<Visualisable> instructions) {
        this.instructions = instructions;
        this.optimized = new ArrayList<>();
        optimized = new ArrayList<>();
        for (iter = 0; iter < instructions.size(); iter++) {
            simplifyRightLeft();
            simplifyIncrDecr();
            simplifySet();
            if (iter < instructions.size()) {
                optimized.add(instructions.get(iter));
            }
        }
        return optimized;
    }


    private OptionalInt simplify(InstructionEnum minus, InstructionEnum plus) {
        if (iter == instructions.size() || (instructions.get(iter) != minus && instructions.get(iter) != plus))
            return OptionalInt.empty();
        int value = 0;
        while (iter < instructions.size() && (instructions.get(iter) == minus || instructions.get(iter) == plus)) {
            if (instructions.get(iter) == plus) {
                value += 1;
            } else if (instructions.get(iter) == minus) {
                value -= 1;
            } else break;
            ++iter;
        }
        return OptionalInt.of(value);
    }

    private boolean simplifyIncrDecr() {
        OptionalInt value = simplify(DECR, INCR);
        if (value.isPresent()) {
            optimized.add(new MultiIncrDecr(value.getAsInt()));
            return true;
        }
        return false;
    }

    private boolean simplifyRightLeft() {
        OptionalInt value = simplify(LEFT, RIGHT);
        if (value.isPresent()) {
            optimized.add(new MultiRightLeft(value.getAsInt()));
            return true;
        }
        return false;
    }

    private boolean simplifySet() {
        if (iter + 2 < instructions.size() && instructions.get(iter) == JUMP && instructions.get(iter + 1) == DECR && instructions.get(iter + 2) == BACK) {
            iter += 3;
            OptionalInt opt = simplify(DECR, INCR);
            if (!opt.isPresent()) {
                optimized.add(new SetCell(0));
            } else {
                optimized.add(new SetCell(opt.getAsInt()));
            }
            return true;
        }
        return false;
    }
}
