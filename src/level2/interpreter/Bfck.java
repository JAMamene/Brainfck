package level2.interpreter;

import com.sun.org.apache.xpath.internal.operations.Bool;
import level2.constants.InstructionEnum;
import level2.exceptions.SyntaxException;

import javax.swing.text.StyledEditorKit;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
        memory = new byte[MAXMEMORYSIZE.get()];
        Arrays.fill(memory, (byte) MINDATASIZE.get());
        this.instructions = instructions;
        this.filename = filename;
        this.filenameIn = filenameIn;
        this.filenameOut = filenameOut;
        instruction = 0;
        pointer = 0;
        readId = 0;
    }

    public int getReadId() {
        return readId;
    }

    public void incrReadId() {
        readId++;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getIn() {
        return in;
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

    public short getMemoryAt(short index) {
        return memory[index];
    } // Only for testing

    public short getPointer() {
        return pointer;
    }

    public void setCase(byte val) {
        memory[pointer] = val;
    }

    public void addToInstruction(int val) {
        instruction += val;
    }

    public void addToPointer(int val) {
        pointer += val;
    }

    public List<InstructionEnum> getInstructions() {
        return instructions;
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

    public String printableCell(short i) {
        return "C" + i + "=" + (memory[i] + MASK.get()) + " / " + (char) (memory[i] + MASK.get()) + "\n";
    }

    /**
     * Main method of the interpreter, reads all the instructions and uses the private methods accordingly.
     */
    public void handle() {
        while (instruction < instructions.size()) {
            instructions.get(instruction).exec(this);
        }
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

    public boolean bound(int i, int j, Boolean up) {
        int compteur = 1;

        for (int a = i; a < j; a++) {

            if (instructions.get(i).getShortcut() == InstructionEnum.JUMP.getShortcut()) {
                if (up) compteur++;
                else compteur--;
            }

            if (instructions.get(i).getShortcut() == InstructionEnum.BACK.getShortcut()) {
                if (up) compteur--;
                else compteur++;
            }

            if ((compteur == 0)) {
                return true;
            }
        }
        return false;
    }
}
