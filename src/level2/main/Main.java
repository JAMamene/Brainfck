package level2.main;


import level2.command.CommandPerform;

/**
 * Main class to test the program with the arguments that you can give
 */
public class Main {

    public static void main(String[] args) throws Exception {
        double start = System.currentTimeMillis();

        CommandPerform perf = new CommandPerform(args); //will perform the actions needed

        perf.performAll(); // Perform all the actions possible

        double end = System.currentTimeMillis() - start;
        System.out.println("PERF : " + end); // Small indicator of the time of execution
    }
}
