package level2.constants;

import level2.writer.*;

/**
 * enum for the supported language to recode brainfuck with
 */
public enum Languages {
    java(new JavaWriter(),
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
            "i += "),                               //multright
    c(new CWriter(),
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
            "mem += "),
    js(new JavascriptWriter(),
            "++mem[i];",
            "--mem[i];",
            "--i;",
            "++i;",
            "mem[i] = prompt(\"type input\").charCodeAt(0);",
            "print(String.fromCharCode(mem[i]));",
            "while (mem[i] != 0) {",
            "}",
            "mem[i] = ",
            "mem[i] += ",
            "mem[i] -= ",
            "i -= ",
            "i += "),
    csharp(new CSharpWriter(),
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
            "i += (char)"),
    python(new PythonWriter(),
            "mem[i]+=1",
            "mem[i]-=1",
            "i-=1",
            "i+=1",
            "mem[i] = ord(sys.stdin.read(1))",
            "print chr(mem[i]",
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
    };

    private BfWriter bfw;
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

    Languages(BfWriter bfw, String incr, String decr, String left, String right, String in, String out, String jump,
              String back, String set, String multincr, String multdecr, String multleft, String multright) {
        this.bfw = bfw;
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

    public BfWriter getCodeClass() {
        return this.bfw;
    }
}
