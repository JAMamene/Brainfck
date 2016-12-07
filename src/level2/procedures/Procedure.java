package level2.procedures;

import level2.constants.Executable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class modelizing a procedure
 */
public class Procedure implements IProcedure {
    private Map<String, List<Executable>> procedures = new HashMap<>();

    /**
     * Method that declares a procedure
     *
     * @param name the name of the procedure declared
     * @param body the list of instructions
     */
    public void declare(String name, List<Executable> body) {
        procedures.put(name, body);
    }

    public List<Executable> getBody(String name) {
        return procedures.get(name);
    }
}
