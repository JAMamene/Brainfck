package level2.writer;

import level2.constants.InstructionEnum;

import java.util.List;

/**
 * Interface of the writer classes, defines the WriteFile method that write a text file or an image
 */


public interface BfWriter {
    void WriteFile(List<InstructionEnum> instructions, String fileName);
}
