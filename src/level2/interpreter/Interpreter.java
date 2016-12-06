package level2.interpreter;

import level2.constants.Executable;
import level2.constants.InstructionEnum;
import level2.constants.Trace;
import level2.constants.Visualisable;
import level2.exceptions.SyntaxException;
import level2.exceptions.VisualisableException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Interpreter {
    protected List<Executable> instructions;
    protected int instruction;
    boolean trace = false;
    private String filename;
    private String filenameOut;
    private String filenameIn;
    private String in;
    private int readId;
    private Map<Integer, Integer> jumpTable;


    public Interpreter(List<Executable> instructions,String filename,String filenameIn,String filenameOut){
        this.instructions = instructions;
        this.filename = filename;
        this.filenameIn = filenameIn;
        this.filenameOut = filenameOut;
        fillJumpTable();
    }
    public void handle(Bfck bfck) {
        while (instruction < instructions.size()) {
            instructions.get(instruction).exec(bfck,this);
            if (trace) {
                Trace.saveState(bfck,this);
            }
        }
        if (trace) Trace.end();
    }

    /**
     * Check if the JumpTo and BackTo are correctly sets
     *
     * @return true if the Instructions are valid (Backto and Jumpto), false otherwise
     */

    public boolean check() {
        Stack<Character> check = new Stack<>();
        int charId = 0;
        for (Executable i : instructions) {
            charId++;
            char c = i.getShortcut();
            if (c == InstructionEnum.JUMP.getShortcut()) {
                check.add(']');
            } else if (c == InstructionEnum.BACK.getShortcut())
                if (!check.empty()) {
                    check.pop();
                } else {
                    throw new SyntaxException("bracket-missmatch", i.getShortcut(), charId);
                }
        }
        if (!check.isEmpty()) {
            throw new SyntaxException("missing-bracket", InstructionEnum.BACK.getShortcut(), instructions.size());
        }
        return true;
    }

    private boolean bound(int i, int j) {
        int compteur = 1;
        for (int a = i + 1; a < j + 1; a++) {
            if (instructions.get(a).getShortcut() == InstructionEnum.JUMP.getShortcut()) {
                compteur++;
            }
            if (instructions.get(a).getShortcut() == InstructionEnum.BACK.getShortcut()) {
                compteur--;
            }
        }
        return instructions.get(j).getShortcut() == InstructionEnum.BACK.getShortcut() && compteur == 0;
    }


    private void fillJumpTable() {
        if (check()) {
            for (int i = 0; i < instructions.size(); i++) {
                if (instructions.get(i).getShortcut() == InstructionEnum.JUMP.getShortcut()) {
                    for (int j = i; j < instructions.size(); j++) {
                        if (bound(i, j)) {
                            jumpTable.put(i, j);
                            jumpTable.put(j, i);
                            break;
                        }
                    }
                }
            }
        }
    }

    public List<Visualisable> getVisualisableInstructions() {
        List<Visualisable> visualisables = new ArrayList<>();
        for (Executable e : instructions) {
            if (!(e instanceof InstructionEnum)) throw new VisualisableException();
            visualisables.add(e);
        }
        return visualisables;
    }

    public List<Visualisable> getOptimizedInstructions() {
        return new Optimizer().optimize(instructions);
    }

    public void incrementInstructions(){
        instruction++;
    }

    public int getInstruction() {
        return instruction;
    }

    public String getIn() {
        return in;
    }

    public int getReadId() {
        return readId;
    }

    public void incrReadId(){
        readId++;
    }

    public void setInstruction(int instruction){
        this.instruction = instruction;
    }

    public int useJumpTable(int instruction){
        return jumpTable.get(instruction);
    }

    public void setTrace() {
        this.trace = true;
    }

    public String getFilename() {
        return filename;
    }

    public int getProgSize(){
        return instructions.size();
    }
}
