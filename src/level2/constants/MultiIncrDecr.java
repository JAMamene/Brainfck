package level2.constants;

import java.awt.*;
import java.util.Optional;

public class MultiIncrDecr extends Refactor implements Visualisable {

    public MultiIncrDecr(int value) {
        super(value);
    }

    @Override
    public Optional<String> getJava() {
        return getClassicRepresentation("mem[i]", value);
    }

    @Override
    public Optional<String> getC() {
        return getClassicRepresentation("*mem", value);
    }

    @Override
    public Optional<String> getJS() {
        return getClassicRepresentation("mem[i]", value);
    }

    @Override
    public Optional<String> getPython() {
        if (value == 0) {
            return null;
        } else if (value < 0) {
            return Optional.of("mem[i] +=" + value);
        } else {
            return Optional.of("mem[i] -=" + Math.abs(value));
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
