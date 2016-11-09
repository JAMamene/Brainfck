package level2.exceptions;

import java.awt.*;

/**
 * Exception class for the error of syntax in the brainfuck program, extends RunTimeException so we are not forced to
 * propagate the exception.
 */
public class SyntaxException extends RuntimeException {
    /**
     * Constructor for syntax exception in text files, same as before, the program is ended as soon as the exception is
     * thrown
     *
     * @param type   type to identify the exception
     * @param c      the invalid char
     * @param charId the id (number of the char) in the file
     */
    public SyntaxException(String type, char c, int charId) {
        switch (type) {
            case "unknown-char":
                System.err.println("Error : Syntax error at charId " + charId + ", char: " + c);
                break;
            case "bracket-missmatch":
                System.err.println("Error : Unexpected bracket at charId " + charId + ", char: " + c);
                break;
            case "missing-bracket":
                System.err.println("Error : Closing brackets missing at " + charId + ", char: " + c);
                break;
        }
        System.exit(4);
    }

    /**
     * Constructor for syntaxException in images
     *
     * @param x     x coordinate of the pixel
     * @param y     y coordinate of the pixel
     * @param color the color of the invalid pixel
     */
    public SyntaxException(int x, int y, Color color) {
        System.err.println("Error : invalid pixel detected at x:" + x + ", y:" + y + ", color : " + color);
        System.exit(4);
    }
}