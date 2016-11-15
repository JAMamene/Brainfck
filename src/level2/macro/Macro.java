package level2.macro;

import level2.constants.InstructionEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Macro {
    private Map<Prototype, List<InstructionEnum>> macros = new HashMap<>();

    public void add(String key, List<InstructionEnum> val) {
        macros.put(new Prototype(key), val);
    }

    public void add(Prototype key, List<InstructionEnum> val) {
        macros.put(key, val);
    }

    public List<InstructionEnum> getMacro(String name) {
        return getPrototype(name).build(macros.get(getPrototype(name)));
    }

    private Prototype getPrototype(String name) {
        return macros.keySet().stream().filter(prototype -> prototype.getName().equals(name)).findFirst().orElse(null);
    }

    public boolean contains(String name) {
        return macros.containsKey(getPrototype(name));
    }
}
