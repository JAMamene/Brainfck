package level2.reader;

import level2.constants.Executable;
import level2.constants.InstructionEnum;
import level2.reader.parser.Parse;
import level2.reader.parser.Text;
import level2.reader.parser.parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModulableReader {
    private String text;

    public List<Executable> readFile(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder res = new StringBuilder();
            String current;
            while ((current = br.readLine()) != null) {
                res.append(current);
                res.append('\n');
            }
            text = res.toString();
            parse();
            return toExecutable(text);
        } catch (IOException e){
            System.out.println(e);
        }
        return null;
    }

    public void parse() {
        for(Parse p : parser.values()){
            p.parse(this);
        }
    }

    public String getText() {
        return text;
    }

    public void replaceText(String regex,String replacement){
        text = text.replaceAll(regex,replacement);
    }

    public List<Executable> toExecutable(String res) {
        List<Executable> instructions = new ArrayList<>();
        Text text = new Text(res);
        Executable exec;
        while ((res = text.nextLine()) != null) {

            for (int i = 0; i < res.length(); i++) {

                if( (exec = findShortInstructions(res.charAt(i))) != null){
                    instructions.add(exec);
                    continue;
                }
                break;
            }
            if((exec = findLongInstructions(res)) != null){
                instructions.add(exec);
                continue;
            }
        }
        return instructions;
    }

    private Executable findShortInstructions(char res) {
        for (InstructionEnum instruction : InstructionEnum.values()) {
            if (instruction.getShortcut() == res) {
                return instruction;
            }
        }
        return null;
    }

    public Executable findLongInstructions(String res) {
        for (InstructionEnum instruction : InstructionEnum.values()) {
            if (instruction.toString().equals(res)) {
                return instruction;
            }
        }
        return null;
    }
}



