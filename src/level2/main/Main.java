package level2.main;


import level2.command.CommandPerform;
import level2.constants.Metrics;

/**
 * Main class to test the program with the arguments that you can give
 */
public class Main {

    public static void main(String[] args) throws Exception {

        CommandPerform perf = new CommandPerform(args); //will perform the actions needed

        perf.performAll(); // Perform all the actions possible
    }
}
