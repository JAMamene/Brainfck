package level4.reader;

import level4.instructions.Executable;
import level4.exceptions.FileException;
import level4.exceptions.SyntaxException;

import java.util.List;

/**
 * Interface of the reader classes, defines the readFile method that reads a text file or an image
 */
public interface BfReader {
    List<Executable> readFile(String fileName) throws FileException, SyntaxException;
}
