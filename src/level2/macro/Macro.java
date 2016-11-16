package level2.macro;

import level2.constants.InstructionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Macro {
    private Map<String, List<InstructionEnum>> macros = new HashMap<>();

    public void add(String key, List<InstructionEnum> val) {
        if (getMacro(key).isEmpty()) {
            macros.put(key, val);
        } else {
            getMacro(key).addAll(val);
        }
    }

    public List<InstructionEnum> getMacro(String name, Integer param) {
        if (param == null) {
            return macros.get(name);
        } else {
            List<InstructionEnum> finalInstructions = new ArrayList<>();
            for (int i = 0; i < param; i++) {
                finalInstructions.addAll(macros.get(name));
            }
            return finalInstructions;
        }
    }

    public void define(String key) {
        macros.put(key, new ArrayList<>());
    }

    private List<InstructionEnum> getMacro(String name) {
        return macros.get(name);
    }

    public boolean contains(String name) {
        return macros.containsKey(name);
    }
}
