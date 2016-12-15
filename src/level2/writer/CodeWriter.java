package level2.writer;


import level2.constants.Languages;
import level2.constants.Visualisable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static java.lang.Enum.valueOf;
import static level2.constants.InstructionEnum.BACK;
import static level2.constants.InstructionEnum.JUMP;

public class CodeWriter implements BfWriter {

    private Languages l;

    public CodeWriter(Languages l) {
        this.l = l;
    }

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

    private String removeExtension(String fileName) {
        fileName = fileName.trim();
        if ((fileName.lastIndexOf('.')) != -1) {
            return fileName.substring(0, fileName.lastIndexOf('.'));
        }
        return fileName;
    }

    @Override
    public void WriteFile(List<Visualisable> instructions, String fileName) {
        BufferedWriter bw;
        BufferedWriter bwHelper;
        try {
            bw = getBw(fileName, l.getExtension());

            if (l == Languages.js) {
                bwHelper = getBw(fileName, ".html");
            } else {
                bwHelper = getBw(fileName, ".sh");
                bwHelper.write("#!/bin/bash\n");
            }
            bwHelper.write(l.getHelper(removeExtension(fileName)));
            bwHelper.close();
            bw.write(l.getHeader(removeExtension(fileName)));
            String indentLevel = l.getIndentLevel();
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
            bw.write(l.getFooter());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
