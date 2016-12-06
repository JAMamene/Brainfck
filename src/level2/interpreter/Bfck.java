package level2.interpreter;

import level2.constants.*;
import level2.exceptions.ExecuteException;
import level2.exceptions.SyntaxException;
import level2.exceptions.VisualisableException;

import java.util.*;

import static level2.constants.Sizes.*;

/**
 * Main class of the interpreter, translate the brainfuck code into java directives
 */
public class Bfck {

    private byte[] memory;
    private short pointer;

    /**
     * Constructor that initializes values and get the List of InstructionEnum (String)
     *
     */
    public Bfck() {
        memory = new byte[MAXMEMORYSIZE.get() + 1];
        Arrays.fill(memory, (byte) MINDATASIZE.get());
        pointer = 0;
    }

    public byte[] getMemory() {
        return memory;
    }

    public byte getCell() {
        return memory[pointer];
    }

    public byte getCellCheck(){
        return memory[pointer];
    }

    public void incrCell(){
        memory[pointer]++;
    }

    public void decrCell(){
        memory[pointer]--;
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

    public void addToPointer(int val) {
        pointer += val;
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

}