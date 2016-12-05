package level2.constants;

import java.awt.*;
import java.util.Optional;

public class MultiRightLeft extends Refactor implements Visualisable {

    public MultiRightLeft(int value) {
        super(value);
    }

    @Override
    public Optional<String> getJava() {
        return getRepresentation("i", value);
    }

    @Override
    public Optional<String> getC() {
        return getRepresentation("mem", value);
    }

    @Override
    public Optional<String> getJS() {
        return getRepresentation("i", value);
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
