package level4.exceptions;

/**
 * Custom exception that handles non visualisable instructions isssues, extends RunTimeException so no propagation is forced by Java
 */
public class VisualisableException extends RuntimeException {
    /**
     * Constructor that will exit the system as soon as the error is printed
     */
    public VisualisableException() {
        System.err.println("Error : You can't translate or rewrite a program that contains functions");
        System.exit(6);
    }
}
