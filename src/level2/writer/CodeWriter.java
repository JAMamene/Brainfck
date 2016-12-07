package level2.writer;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

abstract class CodeWriter implements BfWriter {

    BufferedWriter getBw(String fileName, String extension) {
        // checks if the file has an extension, if it has one, remove it
        File outputFile = new File(removeExtension(fileName) + extension); //ad new extension
        try {
            return new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
