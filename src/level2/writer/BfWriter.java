package level2.writer;

import level2.constants.Executable;

import java.util.List;

/**
 * Interface of the writer classes, defines the WriteFile method that write a text file or an image
 */


public interface BfWriter {
    void WriteFile(List<Executable> instructions, String fileName);
}
