package level2.writer;

import level2.constants.Visualisable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static level2.constants.InstructionEnum.BACK;
import static level2.constants.InstructionEnum.JUMP;

public class JavascriptWriter extends CodeWriter {

    @Override
    public void WriteFile(List<Visualisable> instructions, String fileName) {
        BufferedWriter bw = getBw(fileName, ".js");
        BufferedWriter htmlw;
        File outputFile = new File("index.html"); //File to call the js code from
        try {
            htmlw = new BufferedWriter(new FileWriter(outputFile));
            htmlw.write(getHTML(fileName));
            htmlw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.write(getHeader());
            String indentLevel = "\t";
            for (Visualisable instruction : instructions) {
                if (instruction == BACK && indentLevel.length() >= 1) {
                    indentLevel = indentLevel.substring(0, indentLevel.length() - 1);
                }
                if (instruction.getJava().isPresent()) {
                    bw.write(indentLevel + instruction.getJS().get() + "\n");
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
        return "$(document).ready(function () {\n" +
                "\tvar mem = [];\n" +
                "\tvar i;\n";
    }

    @Override
    protected String getFooter() {
        return "\tfor (var j = 0; j < mem.length; j++) {\n" +
                "\t\tif (mem[j] != 0) {\n" +
                "\t\t\tdocument.getElementById(\"final-output\").innerHTML += \"<br/>\".concat(mem[j]);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "});\n";
    }

    private String getHTML(String fileName) {
        return "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<meta charset=\"ascii\">\n" +
                "\t<title>" + fileName + "</title>\n" +
                "\t<script src=\"" + fileName + ".js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<form>\n" +
                "\t\tOutput : \n" +
                "\t\t<div id=\"output\" ><br/>\t\n" +
                "\t\t</div>\n" +
                "\t\tMemory state :\n" +
                "\t\t<div id=\"final-output\" ><br/>\n" +
                "\t\t</div>\n" +
                "\t</form>\n" +
                "</body>\n" +
                "</html>\n";
    }
}
