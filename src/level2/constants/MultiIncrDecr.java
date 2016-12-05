package level2.constants;

import java.awt.*;
import java.util.Optional;
import java.util.stream.Stream;

public class MultiIncrDecr extends Refactor implements Visualisable {

    public MultiIncrDecr(int value) {
        super(value);
    }

    @Override
    public Optional<String> getJava() {
        return getRepresentation("mem[i]", value);
    }

    @Override
    public Optional<String> getC() {
        return getRepresentation("*mem", value);
    }

    @Override
    public Optional<String> getJS() {
        return getRepresentation("mem[i]", value);
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
