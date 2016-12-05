package level2.argument;


import level2.command.*;
import level2.constants.Languages;
import level2.exceptions.ArgumentException;
import level2.exceptions.FileException;

import java.util.Arrays;

public enum ArgEnum implements Parsable {
    Metrics("--showMetrics") {
        @Override
        public void exec(ArgsCheck arg) {
            arg.addPassiveActions(new MetricsCommand());
            arg.setMetrics(true);
        }
    },
    Rewrite("--rewrite") {
        @Override
        public void exec(ArgsCheck arg) {
            arg.addStoppingActions(new RewriteCommand());
        }
    },
    Translate("--translate") {
        @Override
        public void exec(ArgsCheck arg) {
            arg.addStoppingActions(new TranslateCommand());
        }
    },
    Check("--check") {
        @Override
        public void exec(ArgsCheck arg) {
            arg.addStoppingActions(new CheckCommand());
        }
    },
    Trace("--trace") {
        @Override
        public void exec(ArgsCheck arg) {
            arg.addPassiveActions(new TraceCommand());
        }
    },
    FileIn("-i") {
        @Override
        public void exec(ArgsCheck arg) {
            arg.incrPointer();
            try {
                arg.setIn(arg.getCurrentArg());
            } catch (Exception e) {
                wrongFile();
            }
        }
    },
    FileOut("-o") {
        @Override
        public void exec(ArgsCheck arg) {
            arg.incrPointer();
            try {
                arg.setOut(arg.getCurrentArg());
            } catch (Exception e) {
                wrongFile();
            }
        }
    },
    FileProg("-p") {
        @Override
        public void exec(ArgsCheck arg) {
            arg.incrPointer();
            try {
                arg.setFileName(arg.getCurrentArg());
                arg.setFileExtension(ArgEnum.findFileExtension(arg.getFileName()));
            } catch (Exception e) {
                wrongFile();
            }
        }
    },
    Code("--code") {
        @Override
        public void exec(ArgsCheck arg) {
            arg.incrPointer();
            try {
                Boolean optimize = false;
                System.out.println(arg.getCurrentArg());
                if (!Arrays.asList(Languages.values()).contains(Languages.valueOf(arg.getCurrentArg()))) {
                    throw new ArgumentException("invalid-language");
                } else if (arg.hasArg() && arg.peekArg().equals("optimize")) {
                    optimize = true;
                }
                System.out.println(arg.getCurrentArg());
                arg.addStoppingActions(new WriteCodeCommand(Languages.valueOf(arg.getCurrentArg()), optimize));
                if (optimize) arg.incrPointer();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ArgumentException("missing-arg");
            }
        }
    },
    UnknownArg("???") {
        @Override
        public void exec(ArgsCheck arg) {
            printHelp(arg);
            System.exit(3);
        }
    };

    private String expression;

    ArgEnum(String expression) {
        this.expression = expression;
    }

    private static void wrongFile() {
        throw new FileException("missing-file");
    }

    private static void printHelp(ArgsCheck args) {
        System.err.println("Invalid arguments : " + args.getCurrentArg() + ", use these :\n" +
                "-p + the brainfuck file you want to interpret, \n" +
                "-i + the input file you want to use for in, \n" +
                "-o + the output file you want to use for out, \n" +
                "--rewrite to print to the standard output your program in short syntax,\n" +
                "--translate to get an image representation of you program, \n" +
                "--check to only check if the program is well formed \n" +
                "--showMetrics to print metrics of the program \n" +
                "--trace to create log\n" +
                "--code (java, c) [optimize] to translate the brainfuck code in the appropriate language\n" +
                "and with optimization if you specify the option");
    }

    /**
     * parse the fileame to get his extension
     *
     * @param fileName
     * @return file extension
     */

    private static String findFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    public String getExpression() {
        return expression;
    }
}
