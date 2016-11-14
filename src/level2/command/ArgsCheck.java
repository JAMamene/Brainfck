package level2.command;

import level2.exceptions.WrongFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ArgsCheck {
    private String fileName;
    private String fileExtension;
    private String in;
    private String out;
    private boolean metrics = false;
    private List<Command> actions;

    /**
     * parsing the args array
     * if the parameter is supported it refresh an attribute
     * a file must be specified even if it doesn't exist else throw an exception
     */
    public ArgsCheck(String[] args) {
        HashSet<Command> actions = new HashSet<>();
        boolean checkFile = false;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-p":
                    if (i + 1 >= args.length) {
                        throw new WrongFile("missing-file");
                    } else {
                        i++;
                        fileName = args[i];
                        checkFile = true;
                    }
                    break;

                case "--rewrite":
                    actions.add(new RewriteCommand());
                    break;

                case "--translate":
                    actions.add(new TranslateCommand());
                    break;

                case "--check":
                    actions.add(new CheckCommand());
                    break;

                case "--showMetrics":
                    metrics = true;
                    break;
                case "-i":
                    if (i + 1 >= args.length) {
                        throw new WrongFile("missing-file");
                    } else {
                        i++;
                        in = args[i];
                    }
                    break;
                case "-o":
                    if (i + 1 >= args.length) {
                        throw new WrongFile("missing-file");
                    } else {
                        i++;
                        out = args[i];
                    }
                    break;

                default:
                    printHelp(args);
                    System.exit(3);

            }
            this.actions = new ArrayList<>(actions);
        }
        if (!checkFile) {
            throw new WrongFile("missing-file");
        }
        fileExtension = findFileExtension(fileName);
    }

    /**
     * parse the fileame toget his extension
     *
     * @param fileName
     * @return file extension
     */

    private String findFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    public String getIn() {
        return in;
    }

    public String getOut() {
        return out;
    }

    public boolean getMetrics(){return metrics;}

    public boolean hasActions() {
        return !actions.isEmpty();
    }

    public Command nextAction() {
        Command action = actions.get(0);
        actions.remove(action);
        return action;
    }

    private void printHelp(String[] args) {
        System.out.println("Invalid arguments : " + Arrays.toString(args) + ", use these :\n" +
                "-p + the brainfuck file you want to interpret, \n" +
                "-i + the input file you want to use for in, \n" +
                "-o + the output file you want to use for out, \n" +
                "--rewrite to print to the standard output your program in short syntax,\n" +
                "--translate to get an image representation of you program, \n" +
                "--check to only check if the program is well formed \n" +
                "--showMetrics to print metrics of the program");
    }
}
