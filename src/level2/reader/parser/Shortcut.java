package level2.reader.parser;

import level2.constants.Executable;
import level2.constants.InstructionEnum;

import java.util.*;

public class Shortcut {
    private Map<String, List<Executable>> macro;

    public Shortcut() {
        macro = new HashMap<>();
    }

    public void addMacro(String name, List<Executable> instruction) {
        macro.put(name, instruction);
    }

    public List<Executable> getMacro(String name) {
        if(macro.containsKey(name)) return macro.get(name);
        return null;
    }



}
