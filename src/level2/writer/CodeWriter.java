package level2.writer;


import level2.constants.Languages;
import level2.constants.Visualisable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static level2.constants.InstructionEnum.BACK;
import static level2.constants.InstructionEnum.JUMP;

abstract class CodeWriter implements BfWriter {

    private BufferedWriter getBw(String fileName, String extension) {
        // checks if the file has an extension, if it has one, remove it
        File outputFile = new File(removeExtension(fileName) + extension); //ad new extension
        try {
            return new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void writeCode(List<Visualisable> instructions, String indentLevel, String fileName, String extension, Languages l) {
        BufferedWriter bw = getBw(fileName, extension);
        try {
            bw.write(getHeader(fileName));
            for (Visualisable instruction : instructions) {
                if (instruction == BACK && indentLevel.length() >= 1) {
                    indentLevel = indentLevel.substring(0, indentLevel.length() - 1);
                }
                if (instruction.getCode(l).isPresent()) {
                    bw.write(indentLevel + instruction.getCode(l).get() + "\n");
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

    protected abstract String getHeader(String fileName);

    protected abstract String getFooter();

    protected String removeExtension(String fileName) {
        fileName = fileName.trim();
        if ((fileName.lastIndexOf('.')) != -1) {
            return fileName.substring(0, fileName.lastIndexOf('.'));
        }
        return fileName;
    }

}
