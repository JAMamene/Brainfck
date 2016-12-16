package level2.macro;

import level2.constants.Executable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for the macro instructions
 */
public class Macro {
    /**
     * Map to link the identifier of the macro with its instructions
     */
    private Map<String, List<Executable>> macros = new HashMap<>();

    /**
     * add a new macro or add instructions to an existing one
     *
     * @param key name of the macro
     * @param val the instructions to be added
     */
    public void add(String key, List<Executable> val) {
        if (getMacro(key).isEmpty()) {
            macros.put(key, val);
        } else {
            getMacro(key).addAll(val);
        }
    }

    /**
     * return the instructions of the macro
     *
     * @param name  the name of the macro
     * @param param the number of iterations
     * @return the instructions
     */
    public List<Executable> getMacro(String name, Integer param) {
        if (param == null) {
            return macros.get(name);
        } else {
            List<Executable> finalInstructions = new ArrayList<>();
            for (int i = 0; i < param; i++) {
                finalInstructions.addAll(macros.get(name));
            }
            return finalInstructions;
        }
    }

    /**
     * returns the list of instructions of the macro ( helper method)
     *
     * @param name name of the maro
     * @return the instructions
     */
    private List<Executable> getMacro(String name) {
        return macros.get(name);
    }

    /**
     * put a new macro with no instructions yet in the map
     *
     * @param key name of the macro
     */
    public void define(String key) {
        macros.put(key, new ArrayList<>());
    }

    /**
     * check if the macro exists
     *
     * @param name name of the macro
     * @return true if it exists or false otherwise
     */
    public boolean contains(String name) {
        return macros.containsKey(name);
    }
}
