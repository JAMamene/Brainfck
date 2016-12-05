package level2.constants;

import java.util.Optional;

class Refactor {

    protected int value;

    protected Refactor(int value) {
        this.value = value;
    }

    protected Optional<String> getRepresentation(String memoryRep, int value) {

        if (value == 0) {
            return null;
        } else if (value == 1) {
            return Optional.of("++" + memoryRep + ";");
        } else if (value == -1) {
            return Optional.of("--" + memoryRep + ";");
        } else if (value > 1) {
            return Optional.of(memoryRep + "+=" + value + ";");
        } else {
            return Optional.of(memoryRep + "-=" + Math.abs(value) + ";");
        }
    }
}
