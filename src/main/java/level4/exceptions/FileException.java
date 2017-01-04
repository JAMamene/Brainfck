package level4.exceptions;

import static level4.constants.Sizes.SQUARE_SIZE;

/**
 * Custom exception that handles file isssues, extends RunTimeException so no propagation is forced by Java
 */
public class FileException extends RuntimeException {
    /**
     * Constructor that will exit the system as soon as the error is printed
     *
     * @param type the type of the exception to handle
     */
    public FileException(String type) {
        switch (type) {
            case "dimension-image":
                System.err.println("Error ! Please make sure that your image is square AND that its size is a multiple of " + SQUARE_SIZE.get());
                break;
            case "missing-file":
                System.err.println("Error : You have not specified a file or it doesn't exist!");
                break;
            case "invalid-file":
                System.err.println("Error : File was not correctly parsed");
                break;
            case "unexpected-eof":
                System.err.println("Error : Not enough inputs in file");
                break;
        }
        System.exit(3);
    }
}
