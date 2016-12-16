package level2.Optimization;

import level2.constants.Languages;
import level2.constants.Visualisable;

import java.awt.*;
import java.util.Optional;

/**
 * A visualisable implementation for code generation, concatenation of left and right
 */
public class MultiRightLeft implements Visualisable {

    int value;

    public MultiRightLeft(int value) {
        this.value = value;
    }

    @Override
    public Optional<String> getCode(Languages l) {
        if (value == 0) {
            return Optional.empty();
        } else if (value == 1) {
            return Optional.of(l.getRight());
        } else if (value == -1) {
            return Optional.of(l.getLeft());
        } else if (value > 1) {
            return Optional.of(l.getMultright(value));
        } else {
            return Optional.of(l.getMultleft(Math.abs(value)));
        }
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public char getShortcut() {
        return 0;
    }
}
