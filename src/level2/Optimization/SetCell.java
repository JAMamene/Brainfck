package level2.Optimization;

import level2.constants.Languages;
import level2.constants.Visualisable;

import java.awt.*;
import java.util.Optional;

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
