package level4.constants;

/**
 * enum for the supported language to recode brainfuck with
 * Each of those defines a dictionary of things associated with it
 * the header and footer of code
 * the indentation level where the main code starts
 * the extension
 * all the instructions + the optimized instructions
 * the helper code
 * ***
 * To add a language support just write a new entry in the enum
 */
public enum Languages {
    java(
            "import java.util.*;\n\n" +
                    "public class ",


            "\t\tSystem.out.println();\n" +
                    "\t\tSystem.out.println(\"Memory State : \");\n" +
                    "\t\tfor (int j = 0; j < size; ++j) {\n" +
                    "\t\t\t if (mem[j] != 0) {\n" +
                    "\t\t\t\t System.out.println(\"[\" + j + \"] = \" + (int) mem[j]);\n" +
                    "\t\t\t}\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "}\n",

            "\t\t",

            ".java",

            "++mem[i];",                            //incr
            "--mem[i];",                            //decr
            "--i;",                                 //left
            "++i;",                                 //right
            "mem[i] = reader.next().charAt(0);",    //in
            "System.out.print(mem[i]);",            //out
            "while (mem[i] != 0) {",                //jump
            "}",                                    //back
            "mem[i] = ",                            //set
            "mem[i] += ",                           //multincr
            "mem[i] -= ",                           //multdecr
            "i -= ",                                //multleft
            "i += ") {                              //multright

        @Override
        public String getHeader(String progName) {
            return java.header + progName + " {\n\n" +
                    "\tpublic static void main(String[] args) {\n" +
                    "\t\tint size = 30000;\n" +
                    "\t\tchar[] mem = new char[size];\n" +
                    "\t\tArrays.fill(mem, (char) 0);\n" +
                    "\t\tint i = 0;\n" +
                    "\t\tScanner reader = new Scanner(System.in);\n";
        }

        @Override
        public String getHelper(String progName) {
            return "javac " + progName + ".java\njava " + progName;
        }
    },


    c(
            "#include <stdlib.h>\n" +
                    "#include <stdio.h>\n\n" +
                    "// Careful when entering input, newline is considered as a char\n\n" +
                    "int main(int argc, char **argv) {\n" +
                    "\tunsigned size = 30000;\n" +
                    "\tunsigned char* mem = (unsigned char*) malloc (size);\n" +
                    "\tif (!mem) {\n" +
                    "\t\treturn 1;\n" +
                    "\t}\n" +
                    "\tunsigned char *memory = memory;\n",


            "\tfor (int i = 0; i < size; ++i) {\n" +
                    "\t\t if (mem[i] != 0) {\n" +
                    "\t\t\t printf( \"C[%d] = %d\\n\",i,mem[i]);\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "\tfree(memory);\n" +
                    "\treturn 0;\n" +
                    "}\n",

            "\t",

            ".c",

            "++*mem;",
            "--*mem;",
            "--mem;",
            "++mem;",
            "scanf(\"%c\",mem);",
            "printf(\"%c\",*mem);",
            "while (*mem) {",
            "}",
            "*mem = ",
            "*mem += ",
            "*mem -= ",
            "mem -= ",
            "mem += ") {
        @Override
        public String getHelper(String progName) {
            return "gcc " + progName + ".c\n./a.out\n";
        }
    },

    js(
            "function Bfck() {\n" +
                    "\tmem = Array.apply(null, Array(30000)).map(Number.prototype.valueOf,0);\n" +
                    "\tvar i = 0;",


            "\tfor (var j = 0; j < mem.length; j++) {\n" +
                    "\t\tif (mem[j] != 0) {\n" +
                    "\t\t\tdocument.getElementById(\"final-output\").innerHTML += \"<br/>\".concat(\"[\" + j + \"] = \" + mem[j]);\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "}\n",

            "\t",

            ".js",

            "++mem[i];",
            "--mem[i];",
            "--i;",
            "++i;",
            "mem[i] = prompt(\"type input\").charCodeAt(0);",
            "document.getElementById(\"output\").innerHTML += String.fromCharCode(mem[i]);",
            "while (mem[i] != 0) {",
            "}",
            "mem[i] = ",
            "mem[i] += ",
            "mem[i] -= ",
            "i -= ",
            "i += ") {
        @Override
        public String getHelper(String progName) {
            return "<!doctype html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "\t<meta charset=\"ascii\">\n" +
                    "\t<title>" + progName + "</title>\n" +
                    "\t<script src=\"" + progName + ".js\"></script>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\t<input type=\"button\" value=\"run Bfck\" onclick=\"Bfck();\"><br/><br/>\n" +
                    "\tOutput : <br/>\n" +
                    "\t<div style=\"margin-left:40px\" >\n" +
                    "\t\t<pre id=\"output\"></pre>\n" +
                    "\t</div>\n" +
                    "\tMemory state : <br/>\n" +
                    "\t<div style=\"margin-left:40px\">\n" +
                    "\t\t<pre id=\"final-output\"></pre>\n" +
                    "\t</div>\n" +
                    "</body>\n" +
                    "</html>";
        }
    },


    csharp(
            "using System;\n\n" +
                    "class Bfck {\n\n" +
                    "\tstatic void Main(String[] args) {\n" +
                    "\t\tshort size = 30000;\n" +
                    "\t\tchar[] mem = new char[size];\n" +
                    "\t\tfor ( short j = 0; j < mem.Length ; j++ ) {\n" +
                    "\t\t\tmem[j] = (char) 0;\n" +
                    "\t\t}\n" +
                    "\t\tint i = 0;\n",


            "\t\tConsole.Write(\"\\nMemoryState :\\n\");\n" +
                    "\t\tfor (short j = 0; j < size; ++j) {\n" +
                    "\t\t\t if (mem[j] != 0) {\n" +
                    "\t\t\t\t Console.Write(\"[\" + j + \"] = \" + (int) mem[j] + \"\\n\");\n" +
                    "\t\t\t}\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "}\n",

            "\t\t",

            ".cs",

            "++mem[i];",
            "--mem[i];",
            "--i;",
            "++i;",
            "mem[i] = (char)Console.Read();",
            "Console.Write(mem[i]);",
            "while (mem[i] != 0) {",
            "}",
            "mem[i] = (char)",
            "mem[i] += (char)",
            "mem[i] -= (char)",
            "i -= (char)",
            "i += (char)") {
        @Override
        public String getHelper(String progName) {
            return "mcs " + progName + ".cs #Careful, requires the mono package\n./" + progName + ".exe\n";
        }
    },


    python(
            "import sys\n" +
                    "# Careful, same as C, if you press enter it will add it as an input\n" +
                    "size = 30000\n" +
                    "mem = [0] * size\n" +
                    "i = 0\n",

            "for i in range(0, size):\n" +
                    "\tif mem[i] != 0:\n" +
                    "\t\tprint mem[i]\n",

            "",

            ".py",

            "mem[i]+=1",
            "mem[i]-=1",
            "i-=1",
            "i+=1",
            "mem[i] = ord(sys.stdin.read(1))",
            "sys.stdout.write(chr(mem[i]))",
            "while mem[i] != 0 :",
            "",
            "mem[i]=",
            "mem[i]+=",
            "mem[i]-=",
            "i-=",
            "i+=") {
        @Override
        public String getSet(int value) {
            return python.set + value;
        }

        @Override
        public String getMultincr(int value) {
            return python.multincr + value;
        }

        @Override
        public String getMultdecr(int value) {
            return python.multdecr + value;
        }

        @Override
        public String getMultleft(int value) {
            return python.multleft + value;
        }

        @Override
        public String getMultright(int value) {
            return python.multright + value;
        }

        @Override
        public String getHelper(String progName) {
            return "python " + progName + ".py";
        }
    };

    private String header;
    private String footer;
    private String indentLevel;
    private String extension;
    private String incr;
    private String decr;
    private String left;
    private String right;
    private String in;
    private String out;
    private String jump;
    private String back;
    private String set;
    private String multincr;
    private String multdecr;
    private String multleft;
    private String multright;

    Languages(String header, String footer, String indentLevel, String extension, String incr, String decr,
              String left, String right, String in, String out, String jump,
              String back, String set, String multincr, String multdecr, String multleft, String multright) {
        this.header = header;
        this.footer = footer;
        this.indentLevel = indentLevel;
        this.extension = extension;
        this.incr = incr;
        this.decr = decr;
        this.left = left;
        this.right = right;
        this.in = in;
        this.out = out;
        this.jump = jump;
        this.back = back;
        this.set = set;
        this.multincr = multincr;
        this.multdecr = multdecr;
        this.multleft = multleft;
        this.multright = multright;
    }

    public String getIncr() {
        return incr;
    }

    public String getDecr() {
        return decr;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    public String getIn() {
        return in;
    }

    public String getOut() {
        return out;
    }

    public String getJump() {
        return jump;
    }

    public String getBack() {
        return back;
    }

    public String getSet(int value) {
        return set + value + ";";
    }

    public String getMultincr(int value) {
        return multincr + value + ";";
    }

    public String getMultdecr(int value) {
        return multdecr + value + ";";
    }

    public String getMultleft(int value) {
        return multleft + value + ";";
    }

    public String getMultright(int value) {
        return multright + value + ";";
    }

    public String getHeader(String progName) {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    /**
     * Helper file with the compilation lines (or a website for javascript)
     *
     * @param progName name of the program wihtout extension
     * @return the code to add to the script
     */
    abstract public String getHelper(String progName);

    public String getExtension() {
        return extension;
    }

    public String getIndentLevel() {
        return indentLevel;
    }
}
