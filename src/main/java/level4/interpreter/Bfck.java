package level4.interpreter;

import java.util.Arrays;

import static level4.constants.Sizes.*;

/**
 * Main class of the interpreter, translate the brainfuck code into java directives
 */
public class Bfck implements Memory {
    private byte[] memory;
    private short pointer;
    private int instruction;

    /**
     * Constructor that initializes values and get the List of InstructionEnum (String)
     */
    public Bfck() {
        memory = new byte[MAXMEMORYSIZE.get() + 1];
        Arrays.fill(memory, (byte) MINDATASIZE.get());
        pointer = 0;
    }

    @Override
    public byte[] getMemory() {
        return memory;
    }

    @Override
    public byte getCell() {
        instruction++;
        return memory[pointer];
    }

    @Override
    public byte getCellCheck() {
        return memory[pointer];
    }

    @Override
    public void incrCell() {
        memory[pointer]++;
        instruction++;
    }

    @Override
    public void decrCell() {
        memory[pointer]--;
        instruction++;
    }

    @Override
    public void left() {
        pointer--;
        instruction++;
    }

    @Override
    public void right() {
        pointer++;
        instruction++;
    }

    @Override
    public byte getMemoryAt(short index) {
        return memory[index];
    } // Only for testing

    @Override
    public short getPointer() {
        return pointer;
    }

    /**
     * Main method of the interpreter, reads all the instructions and uses the private methods accordingly.
     */

    @Override
    public void setPointer(short val) {
        pointer = val;
    }

    @Override
    public void setCase(byte val) {
        memory[pointer] = val;
        instruction++;
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

    @Override
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
}