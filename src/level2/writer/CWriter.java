package level2.writer;

import level2.constants.Languages;
import level2.constants.Visualisable;

import java.util.List;

public class CWriter extends CodeWriter {

    @Override
    public void WriteFile(List<Visualisable> instructions, String fileName) {
        writeCode(instructions, "\t", fileName, ".c", Languages.c);
    }

    @Override
    protected String getHeader(String fileName) {
        return "#include <stdlib.h>\n" +
                "#include <stdio.h>\n\n" +
                "// Careful when entering input, newline is considered as a char\n\n" +
                "int main(int argc, char **argv) {\n" +
                "\tunsigned size = 30000;\n" +
                "\tunsigned char* mem = (unsigned char*) malloc (size);\n" +
                "\tif (!mem) {\n" +
                "\t\treturn 1;\n" +
                "\t}\n" +
                "\tunsigned char *memory = memory;\n";
    }

    @Override
    protected String getFooter() {
        return "" +
                "\tfor (int i = 0; i < size; ++i) {\n" +
                "\t\t if (mem[i] != 0) {\n" +
                "\t\t\t printf( \"C[%d] = %d\\n\",i,mem[i]);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\tfree(memory);\n" +
                "\treturn 0;\n" +
                "}\n";
    }
}
