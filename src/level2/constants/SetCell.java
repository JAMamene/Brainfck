package level2.constants;

import java.awt.*;
import java.util.Optional;

public class SetCell extends Refactor implements Visualisable {

    public SetCell(int value) {
        super(value);
    }

    @Override
    public Optional<String> getJava() {
        return getRepresentationSet("mem[i]");
    }

    @Override
    public Optional<String> getC() {
        return getRepresentationSet("*mem");
    }

    @Override
    public Optional<String> getJS() {
        return getRepresentationSet("mem[i]");
    }

    @Override
    public Optional<String> getPython() {
        return getRepresentationSet("mem[i]");
    }

    private Optional<String> getRepresentationSet(String memoryRep) {
        if (value < 0) {
            return Optional.empty();
        } else if (value == 0) {
            return Optional.of(memoryRep + "=0");
        } else {
            return Optional.of(memoryRep + "=" + value);
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
