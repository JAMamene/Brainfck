package level2.reader;


import level2.constants.InstructionEnum;
import level2.exceptions.SyntaxException;
import level2.exceptions.WrongFile;
import level2.macro.Macro;
import level2.macro.Prototype;

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
    private Prototype currentMacro;

    /**
     * A function that reads a file that contains either syntaxes of brainfuck (or both)
     *
     * @param fileName the main of the file to be read
     * @return a String containing all the instructions in short format
     */
    @Override
    public List<InstructionEnum> ReadFile(String fileName) {
        try {
            List<InstructionEnum> instructions = new ArrayList<>();
            macro = new Macro();
            String str;
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while ((str = br.readLine()) != null) {
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
                    System.out.println(macro.getMacro(currentMacro));
                } else instructions.addAll(writeInstructions(str));
            }
            return instructions;
        } catch (FileNotFoundException e) {
            throw new WrongFile("missing-file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Prototype defineMacro(String str) {
        String name;
        Integer parameter = null;
        String[] args = str.split(" ");
        if (args.length == 2) {
            name = args[1];
        } else if (args.length == 3) {
            name = args[1];
            try {
                parameter = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                throw new SyntaxException("macro-syntax", args[2].charAt(0), charId + 2);
            }
        } else {
            throw new SyntaxException("macro-syntax", args[0].charAt(0), charId);
        }
        charId += args.length;
        Prototype proto = new Prototype(name,parameter);
        macro.define(proto);
        return proto;
    }

    private List<InstructionEnum> writeInstructions(String str) {
        Character c;
        List<InstructionEnum> instructions = new ArrayList<>();
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
        } else if (macro.contains(str.split("#")[0])) {
            instructions.addAll(macro.getMacro(str.split("#")[0]));
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
                if (!found) throw new SyntaxException("unknown-char", c, charId);
            }
        }
        return instructions;
    }
}