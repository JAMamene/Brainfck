package level2.writer;

import level2.constants.Visualisable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import static level2.constants.InstructionEnum.BACK;
import static level2.constants.InstructionEnum.JUMP;

public class CWriter extends CodeWriter {

    @Override
    public void WriteFile(List<Visualisable> instructions, String fileName) {
        BufferedWriter bw = getBw(fileName, ".c");
        try {
            bw.write(getHeader());
            String indentLevel = "\t";
            for (Visualisable instruction : instructions) {
                if (instruction == BACK && indentLevel.length() >= 1) {
                    indentLevel = indentLevel.substring(0, indentLevel.length() - 1);
                }
                if (!(instruction.getJava() == null)) {
                    bw.write(indentLevel + instruction.getC() + "\n");
                }
                if (instruction == JUMP) {
                    indentLevel += '\t';
                }
            }
            bw.write(getFooter());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getHeader() {
        return "" +
                "#include <stdio.h>\n" +
                "int main(int argc, char **argv) {\n" +
                "\tunsigned size = 30000;\n" +
                "\tchar* mem = (char*) malloc (size);\n";
    }

    @Override
    protected String getFooter() {
        return "" +
                "\tfor (int i = 0; i < size; ++i) {\n" +
                "\t\t if (mem[i] != 0) {\n" +
                "\t\t\t printf(%c, mem[i]);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\treturn 0;\n" +
                "}\n";
    }
}
