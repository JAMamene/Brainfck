package level2.procedures;

import level2.constants.Executable;

public interface Parametrized extends Executable {
    Executable getFunction(short... parameter);
}
