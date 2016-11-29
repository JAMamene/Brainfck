package level2.interpreter;

import level2.constants.InstructionEnum;
import level2.constants.Metrics;
import level2.constants.Trace;
import level2.exceptions.SyntaxException;

import java.util.*;

import static level2.constants.Sizes.*;

/**
 * Main class of the interpreter, translate the brainfuck code into java directives
 */
public class Bfck {
    private String filename;
    private String filenameOut;
    private String filenameIn;
    private String in;
    private int readId;
    private boolean trace = false;
    private Map<Integer, Integer> jumpTable;
    private byte[] memory;
    private List<InstructionEnum> instructions;
    private int instruction;
    private short pointer;

    /**
     * Constructor that initializes values and get the List of InstructionEnum (String)
     *
     * @param instructions an array of Instruction that contains all the instructions of the brainfck program
     */
    public Bfck(List<InstructionEnum> instructions, String filename, String filenameIn, String filenameOut) {
        memory = new byte[MAXMEMORYSIZE.get()+1];
        Arrays.fill(memory, (byte) MINDATASIZE.get());
        this.instructions = instructions;
        this.filename = filename;
        this.filenameIn = filenameIn;
        this.filenameOut = filenameOut;
        instruction = 0;
        pointer = 0;
        readId = 0;
        jumpTable = new HashMap<>();
        fillJumpTable();
    }

    public int getReadId() {
        return readId;
    }

    public void incrReadId() {
        readId++;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getFilenameOut() {
        return filenameOut;
    }

    public String getFilenameIn() {
        return filenameIn;
    }

    public String getFilename() {
        return filename;
    }

    public byte getCell() {
        return memory[pointer];
    }

    public int getInstruction() {
        return instruction;
    }

    public void setInstruction(int val) {
        instruction = val;
    }

    public short getMemoryAt(short index) {
        return memory[index];
    } // Only for testing

    public short getPointer() {
        return pointer;
    }

    public void setCase(byte val) {
        memory[pointer] = val;
    }

    public void setTrace() {
        this.trace = true;
    }

    public void incrementInstructions() {
        instruction += 1;
    }

    public void addToPointer(int val) {
        pointer += val;
    }

    public List<InstructionEnum> getInstructions() {
        return instructions;
    }

    public Map<Integer, Integer> getJumpTable() {
        return jumpTable;
    }

    public void activeTrace() {
        trace = true;
    }

    /**
     * Displays all the cells that are not zero
     *
     * @return the String generated
     */
    @Override
    public String toString() {
        StringBuilder rtrn = new StringBuilder("MEMORY : \n");
        for (short i = 0; i < memory.length; ++i) {
            if (memory[i] != MINDATASIZE.get()) {
                rtrn.append(printableCell(i));
            }
        }
        return rtrn.toString();
    }

    public String toDebugString() {
        StringBuilder rtrn = new StringBuilder("[");
        Boolean notFound = true;
        for (byte aMemory : memory) {
            if (aMemory != MINDATASIZE.get() || notFound) {
                if (aMemory != MINDATASIZE.get()) notFound = false;
                rtrn.append(aMemory + MASK.get());
                rtrn.append(",");
            }
        }
        if (notFound) return ("[]");
        rtrn.setLength(rtrn.length() - 1);
        rtrn.append("]");
        return rtrn.toString();
    }

    private String printableCell(short i) {
        return "C" + i + "=" + (memory[i] + MASK.get()) + " / " + (char) (memory[i] + MASK.get()) + "\n";
    }

    /**
     * Main method of the interpreter, reads all the instructions and uses the private methods accordingly.
     */
    public void handle() {
        if(Metrics.isOn())Metrics.setProgSize(instructions.size());
        while (instruction < instructions.size()) {
            instructions.get(instruction).exec(this);
            if(Metrics.isOn())Metrics.incrExecMove();
            if (trace) {
                Trace.saveState(this);
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
        for (InstructionEnum i : instructions) {
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
}