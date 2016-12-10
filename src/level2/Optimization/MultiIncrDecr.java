package level2.Optimization;

import level2.constants.Languages;
import level2.constants.Visualisable;

import java.awt.*;
import java.util.Optional;

public class MultiIncrDecr implements Visualisable {

    private int value;

    public MultiIncrDecr(int value) {
        this.value = value;
    }

    @Override
    public Optional<String> getCode(Languages l) {
        if (value == 0) {
            return Optional.empty();
        } else if (value == 1) {
            return Optional.of(l.getIncr());
        } else if (value == -1) {
            return Optional.of(l.getDecr());
        } else if (value > 1) {
            return Optional.of(l.getMultincr(value));
        } else {
            return Optional.of(l.getMultdecr(Math.abs(value)));
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
