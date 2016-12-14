package level2.reader;


import level2.constants.Executable;
import level2.constants.InstructionEnum;
import level2.exceptions.FileException;
import level2.exceptions.SyntaxException;
import level2.macro.Macro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A class that reads from a file, implements bfReader
 */
public class InstructionReader implements BfReader {

    private int charId = 0;
    private Macro macro;
    private String currentMacro;

    /**
     * A function that reads a file that contains either syntaxes of brainfuck (or both)
     *
     * @param fileName the main of the file to be read
     * @return a String containing all the instructions in short format
     */
    @Override
    public List<Executable> readFile(String fileName) {
        try {
            List<Executable> instructions = new ArrayList<>();
            macro = new Macro();
            String str;
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while ((str = br.readLine()) != null) {
                if (str.isEmpty()) continue;
                if (str.charAt(0) == '{') {
                    currentMacro = defineMacro(str);
                    continue;
                } else if (str.charAt(0) == '}') {
                    currentMacro = null;
                    charId++;
                    continue;
                }
                if (currentMacro != null) {
                    macro.add(currentMacro, writeInstructions(str));
                } else instructions.addAll(writeInstructions(str));
            }
            return instructions;
        } catch (FileNotFoundException e) {
            throw new FileException("missing-file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String defineMacro(String str) {
        String name;
        String[] args = str.split(" ");
        if (args.length == 2) {
            name = args[1];
        } else {
            throw new SyntaxException("macro-syntax", args[0].charAt(0)+"", charId);
        }
        charId += args.length;
        macro.define(name);
        return name;
    }

    private List<Executable> writeInstructions(String str) {
        Character c;
        List<Executable> instructions = new ArrayList<>();
        str = str.replaceAll("\\s+", ""); // trim spaces
        // if we read a long instruction
        if (Stream.of(InstructionEnum.values())
                .map(InstructionEnum::name)
                .collect(Collectors.toList()).contains(str.split("#")[0])) { // Handle comments
            // Better than a switch statement
            // Get all information from enum Instruction
            for (InstructionEnum i : InstructionEnum.values()) {
                if (str.split("#")[0].equals(i.name())) instructions.add(i);
            }
            charId++;
        } else if (macro.contains(str.split("#")[0].split("%")[0])) {
            String macroArg = str.split("#")[0];
            Integer param = null;
            String[] split = macroArg.split("%");
            if (split.length == 2) {
                try {
                    param = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    throw new SyntaxException("macro-syntax", split[0].charAt(0)+"", charId);
                }
            }
            charId += split.length;
            instructions.addAll(macro.getMacro(str.split("#")[0].split("%")[0], param));
        } else { // if we read a shortcut
            for (int j = 0; j < str.length(); j++) {
                c = str.charAt(j);
                // Better than a switch statement
                // Boolean to check if we found the char
                Boolean found = false;
                // Get all information from enum Instruction
                for (InstructionEnum i : InstructionEnum.values()) {
                    if (c == (i.getShortcut())) {
                        instructions.add(i);
                        found = true;
                        break;
                    }
                }
                if (c == '#') { // Comment block
                    break;
                }
                charId++;
                if (!found) throw new SyntaxException("unknown-char", c.toString(), charId);
            }
        }
        return instructions;
    }
}