package level2.writer;

import level2.constants.Visualisable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import static level2.constants.InstructionEnum.BACK;
import static level2.constants.InstructionEnum.JUMP;

public class PythonWriter extends CodeWriter {

    @Override
    public void WriteFile(List<Visualisable> instructions, String fileName) {
        BufferedWriter bw = getBw(fileName, ".py");
        try {
            bw.write(getHeader(fileName));
            String indentLevel = "\t\t";
            for (Visualisable instruction : instructions) {
                if (instruction == BACK && indentLevel.length() >= 2) {
                    indentLevel = indentLevel.substring(0, indentLevel.length() - 1);
                }
                if (instruction.getJava().isPresent()) {
                    bw.write(indentLevel + instruction.getPython().get() + "\n");
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
    protected String getHeader(String fileName) {
        return "";
    }

    @Override
    protected String getFooter() {
        return "";
    }
}
