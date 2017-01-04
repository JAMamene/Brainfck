package level4.writer;

import level4.instructions.Visualisable;

import java.util.List;

/**
 * Interface of the writer classes, defines the WriteFile method that write a text file or an image
 */


public interface BfWriter {
    void WriteFile(List<Visualisable> instructions, String fileName);
}
