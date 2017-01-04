package level2.reader;

import level2.constants.Executable;
import level2.constants.InstructionEnum;
import level2.exceptions.FileException;
import level2.exceptions.SyntaxException;
import level2.procedures.Function;
import level2.procedures.Procedure;
import level2.reader.parser.Parse;
import level2.reader.parser.Shortcut;
import level2.reader.parser.Text;
import level2.reader.parser.parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModulableReader implements BfReader {
    private String text;
    private int charId = 0;
    private Shortcut shortcut;

    public List<Executable> readFile(String filename) {
        try {
            shortcut= new Shortcut();
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
        } catch (FileNotFoundException e) {
            throw new FileException("missing-file");
        } catch (IOException e) {
            e.printStackTrace();
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
        List<Executable> execs;
        while ((res = text.nextLine()) != null) {

            if((execs = findShortInstructions(res)) != null){
                instructions.addAll(execs);
                continue;
            }
            if((exec = findLongInstructions(res)) != null){
                instructions.add(exec);
                charId++;
                continue;
            }
            if((exec = shortcut.getExecutable(res)) != null){
                instructions.add(exec);
                charId++;
                continue;
            }
            throw new SyntaxException("unknown-char", res, charId);
        }
        return instructions;
    }

    private List<Executable> findShortInstructions(String res) {
        List<Executable> instructions = new ArrayList<>();
        for(int i = 0;i<res.length();i++){

        }
        return instructions;
    }

    private Executable findShort(char res){
        for(InstructionEnum instruction : InstructionEnum.values()){
            if(res == instruction.getShortcut()){
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

    public void addProc(String name,String instruction){
        shortcut.addProcedure(name,new Procedure(toExecutable(instruction)));
    }

    public void addFunc(String name,String instruction){
        shortcut.addProcedure(name,new Function(toExecutable(instruction)));
    }
}



