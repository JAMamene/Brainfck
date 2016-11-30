package level2.reader;

import level2.constants.Executable;
import level2.exceptions.SyntaxException;
import level2.exceptions.WrongFile;

import java.util.List;

/**
 * Interface of the reader classes, defines the readFile method that reads a text file or an image
 */
public interface BfReader {
    List<Executable> readFile(String fileName) throws WrongFile, SyntaxException;
}
