package level4.utils;

import level4.instructions.Executable;

public interface Parametrized extends Executable {
    Executable getFunction(short... parameter);
}
