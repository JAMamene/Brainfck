package level2.writer;

import level2.constants.Executable;
import level2.constants.InstructionEnum;

import java.util.List;

/**
 * A class that rewrite shorter instructions, implements bfWriter
 */

public class InstructionWriter implements BfWriter {

    /**
     * print the short version of a program to the standard output
     *  @param instructions list of instructions to print
     * @param fileName     the output file (actually not used)
     */
    public void WriteFile(List<Executable> instructions, String fileName) {
        for (Executable instruction : instructions) {
            System.out.print(instruction.getShortcut());
        }
        System.out.println();
    }
}
