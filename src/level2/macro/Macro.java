package level2.macro;

import level2.constants.InstructionEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Macro {
    private Map<String, List<InstructionEnum>> macros = new HashMap<>();

    public void add (String key, List<InstructionEnum> val) {
        macros.put(key, val);
    }

    public List<InstructionEnum> getMacro (String name) {
        return macros.get(name);
    }

    public boolean contains (String name) {
        return macros.containsKey(name);
    }
}
