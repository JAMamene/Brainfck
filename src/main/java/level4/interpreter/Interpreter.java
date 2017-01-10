package level4.interpreter;

import level4.instructions.Executable;
import level4.instructions.InstructionEnum;
import level4.constants.Trace;
import level4.instructions.Visualisable;
import level4.exceptions.SyntaxException;
import level4.exceptions.VisualisableException;

import java.util.*;

public class Interpreter {
    protected List<Executable> instructions;
    protected int instruction;
    boolean trace = false;
    private String in;
    private int readId;
    private Map<Integer, Integer> jumpTable;


    public Interpreter(List<Executable> instructions) {
        this.instructions = instructions;
        this.instruction = 0;
        jumpTable = new HashMap<>();
        fillJumpTable();
    }

    public void handle(Memory bfck) {
        while (instruction < instructions.size()) {
            instructions.get(instruction).exec(bfck, this);
        }
        if (trace) Trace.end();
    }

    /**
     * Check if the JumpTo and BackTo are correctly sets
     *
     * @return true if the instructions are valid (Backto and Jumpto), false otherwise
     */

    public boolean check() {
        Stack<Character> check = new Stack<>();
        int charId = 0;
        for (Executable i : instructions) {
            charId++;
            if (i == InstructionEnum.JUMP) {
                check.add(']');
            } else if (i == InstructionEnum.BACK)
                if (!check.empty()) {
                    check.pop();
                } else {
                    throw new SyntaxException("bracket-missmatch", i.toString(), charId);
                }
        }
        if (!check.isEmpty()) {
            throw new SyntaxException("missing-bracket", InstructionEnum.BACK.toString(), instructions.size());
        }
        return true;
    }

    private boolean bound(int i, int j) {
        int compteur = 1;
        for (int a = i + 1; a < j + 1; a++) {
            if (instructions.get(a) == InstructionEnum.JUMP) {
                compteur++;
            }
            if (instructions.get(a) == InstructionEnum.BACK) {
                compteur--;
            }
        }
        return instructions.get(j) == InstructionEnum.BACK && compteur == 0;
    }


    private void fillJumpTable() {
        if (check()) {
            for (int i = 0; i < instructions.size(); i++) {
                if (instructions.get(i) == InstructionEnum.JUMP) {
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
            if (!(e instanceof Visualisable)) throw new VisualisableException();
            visualisables.add((Visualisable) e);
        }
        return visualisables;
    }

    public List<Visualisable> getOptimizedInstructions() {
        return new Optimizer().optimize(getVisualisableInstructions());
    }

    public void incrementInstructions() {
        instruction++;
    }

    public int getInstruction() {
        return instruction;
    }

    public void setInstruction(int instruction) {
        this.instruction = instruction;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public int getReadId() {
        return readId;
    }

    public void incrReadId() {
        readId++;
    }

    public int useJumpTable(int instruction) {
        return jumpTable.get(instruction);
    }

    public void setTrace() {
        this.trace = true;
    }

    public int getInstructionSize() {
        return instructions.size();
    }

    public List<Executable> getInstructions(){
        return instructions;
    }
}
