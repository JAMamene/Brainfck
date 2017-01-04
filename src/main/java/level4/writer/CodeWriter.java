package level4.writer;


import level4.constants.Languages;
import level4.instructions.Visualisable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static level4.instructions.InstructionEnum.BACK;
import static level4.instructions.InstructionEnum.JUMP;

/**
 * an implementation of writer to create generated code of a language
 */
public class CodeWriter implements BfWriter {

    /**
     * language to generate code from
     */
    private Languages l;

    public CodeWriter(Languages l) {
        this.l = l;
    }

    /**
     * puts a bufferedwriter on a file with an extension
     *
     * @param fileName  name of the file with optional extension
     * @param extension new extension
     * @return a bufferedwriter on the file created or selected
     */
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

    /**
     * removes the extension of a file
     *
     * @param fileName name of the file with optional extension
     * @return String new name
     */
    private String removeExtension(String fileName) {
        fileName = fileName.trim();
        if ((fileName.lastIndexOf('.')) != -1) {
            return fileName.substring(0, fileName.lastIndexOf('.'));
        }
        return fileName;
    }

    /**
     * this implementation creates two files, a helper and a file for the code
     * the helper is used to have a launcher fo the code generated depending on the language
     *
     * @param instructions list of visualisable to print (accordingly to the language) to the file
     * @param fileName     name of the file
     */
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
