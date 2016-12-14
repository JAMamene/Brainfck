package level2.writer;


import level2.constants.Languages;
import level2.constants.Visualisable;

import java.util.List;

public class CSharpWriter extends CodeWriter {
    @Override
    public void WriteFile(List<Visualisable> instructions, String fileName) {
        writeCode(instructions, "\t\t", fileName, ".cs", Languages.csharp);
    }

    @Override
    protected String getHeader(String fileName) {
        return "using System;\n\n" +
                "class " + removeExtension(fileName) + " {\n\n" +
                "\tstatic void Main(String[] args) {\n" +
                "\t\tshort size = 30000;\n" +
                "\t\tchar[] mem = new char[size];\n" +
                "\t\tfor ( short j = 0; j < mem.Length ; j++ ) {\n" +
                "\t\t\tmem[j] = (char) 0;\n" +
                "\t\t}\n" +
                "\t\tint i = 0;\n";
    }

    @Override
    protected String getFooter() {
        return "\t\tConsole.Write(\"\\nMemoryState :\\n\");\n" +
                "\t\tfor (short j = 0; j < size; ++j) {\n" +
                "\t\t\t if (mem[j] != 0) {\n" +
                "\t\t\t\t Console.Write(\"[\" + j + \"] = \" + (int) mem[j] + \"\\n\");\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";
    }
}
