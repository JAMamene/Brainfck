package level2.macro;

import level2.constants.InstructionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Macro {
    private Map<Prototype, List<InstructionEnum>> macros = new HashMap<>();

    public void add(Prototype key, List<InstructionEnum> val) {
        if (getMacro(key).isEmpty()) {
            macros.put(key, val);
        }
        else {
            getMacro(key).addAll(val);
        }
    }

    public void define(Prototype key) {
        macros.put(key, new ArrayList<>());
    }

    public List<InstructionEnum> getMacro(Prototype proto) {
        return proto.build(macros.get(proto));
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
