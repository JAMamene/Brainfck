package level4.Optimization;

import level4.constants.Languages;
import level4.instructions.Visualisable;

import java.awt.*;
import java.util.Optional;

/**
 * A visualisable implementation for code generation, set a cell to zero or a specific value
 */
public class SetCell implements Visualisable {

    private int value;

    public SetCell(int value) {
        this.value = value;
    }

    @Override
    public Optional<String> getCode(Languages l) {
        if (value < 0) {
            return Optional.empty();
        } else {
            return Optional.of(l.getSet(value));
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
