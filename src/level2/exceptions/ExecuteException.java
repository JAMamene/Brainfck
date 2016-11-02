package level2.exceptions;

/**
 * Exception class for the error of execution in the brainfuck program, extends RunTimeException so we are not forced to
 * propagate the exception.
 */
public class ExecuteException extends RuntimeException {

    /**
     * The constructor of the exception, we only have a constructor here because the program stops where an exception is
     * thrown (as specified). We print to the err output to prevent writing in file when out is redirected
     *
     * @param type        string identifying the type of the exception
     * @param pointer     the cell of the memory where the problem happened
     * @param instruction the number of the instruction where the problem, useful for debug
     */
    public ExecuteException(String type, int pointer, int instruction) {
        instruction++;
        switch (type) {
            case "cell-overflow":
                System.err.println("Error : cell overflow at memory " + pointer + ", instruction: " + instruction);
                System.exit(1);
                break;
            case "cell-underflow":
                System.err.println("Error : cell underflow at memory " + pointer + ", instruction: " + instruction);
                System.exit(1);
                break;
            case "invalid-input":
                System.err.println("Error : input not recognized " + pointer + ", instruction: " + instruction);
                System.exit(1);
                break;
            case "memory-underflow":
                System.err.println("Error : memory underflow at memory at instruction: " + instruction);
                System.exit(2);
                break;
            case "memory-overflow":
                System.err.println("Error : memory overflow at instruction: " + instruction);
                System.exit(2);
                break;
        }
    }
}
