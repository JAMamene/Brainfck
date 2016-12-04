package level2.writer;

import level2.constants.Visualisable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import static level2.constants.InstructionEnum.BACK;
import static level2.constants.InstructionEnum.JUMP;

public class JavaWriter extends CodeWriter {

    @Override
    public void WriteFile(List<Visualisable> instructions, String fileName) {
        BufferedWriter bw = getBw(fileName, ".java");
        try {
            bw.write(getHeader());
            String indentLevel = "\t\t";
            for (Visualisable instruction : instructions) {
                if (instruction == BACK && indentLevel.length() >= 2) {
                    indentLevel = indentLevel.substring(0, indentLevel.length() - 1);
                }
                bw.write(indentLevel + instruction.getJava() + "\n");
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
        return "import java.util.*;\n\n" +
                "public class Brainfuck {\n\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tint size = 30000;\n" +
                "\t\tchar[] mem = new char[size];\n" +
                "\t\tArrays.fill(mem, 0);\n" +
                "\t\tint i = 0;\n" +
                "\t\tScanner sc = new Scanner(System.in);\n";
    }

    @Override
    protected String getFooter() {
        return "\t\tfor (int j = 0; j < size; ++j) {\n" +
                "\t\t\t if (mem[j] != 0) {\n" +
                "\t\t\t\t System.out.println(mem[j]);\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";
    }
}
