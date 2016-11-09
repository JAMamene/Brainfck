package level2.reader;


import level2.constants.InstructionEnum;
import level2.exceptions.SyntaxException;
import level2.exceptions.WrongFile;

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


    /**
     * A function that reads a file that contains either syntaxes of brainfuck (or both)
     *
     * @param fileName the main of the file to be read
     * @return a String containing all the instructions in short format
     */
    @Override
    public List<InstructionEnum> ReadFile(String fileName) {
        try {
            int charId = 0;
            List<InstructionEnum> instructions = new ArrayList<>();
            String str;
            Character c;
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while ((str = br.readLine()) != null) {
                str = str.replaceAll("\\s+","");
                // if we read a long instruction
                if (Stream.of(InstructionEnum.values())
                        .map(InstructionEnum::name)
                        .collect(Collectors.toList()).contains(str.split("#")[0])) {
                    // Better than a switch statement
                    // Get all information from enum Instruction
                    for (InstructionEnum i : InstructionEnum.values()) {
                        if (str.split("#")[0].equals(i.name())) instructions.add(i);
                    }
                    charId++;
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
            }
            return instructions;
        } catch (FileNotFoundException e) {
            throw new WrongFile("missing-file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}