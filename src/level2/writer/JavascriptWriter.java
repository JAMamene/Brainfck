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
        File outputFile = new File(removeExtension(fileName) + ".html"); //File to call the js code from
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
        return "function Bfck() {\n" +
                "\tmem = Array.apply(null, Array(30000)).map(Number.prototype.valueOf,0);\n" +
                "\tvar i = 0;";
    }

    @Override
    protected String getFooter() {
        return "\tfor (var j = 0; j < mem.length; j++) {\n" +
                "\t\tif (mem[j] != 0) {\n" +
                "\t\t\tdocument.getElementById(\"final-output\").innerHTML += \"<br/>\".concat(mem[j]);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
    }

    private String getHTML(String fileName) {
        return "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<meta charset=\"ascii\">\n" +
                "\t<title>" + removeExtension(fileName) + "</title>\n" +
                "\t<script src=\"" + removeExtension(fileName) + ".js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<input type=\"button\" value=\"run Bfck\" onclick=\"Bfck();\"><br/><br/>\n" +
                "\tOutput : <br/>\n" +
                "\t<div style=\"margin-left:40px\" >\n" +
                "\t\t<pre id=\"output\"></pre>\n" +
                "\t</div>\n" +
                "\tMemory state : <br/>\n" +
                "\t<div style=\"margin-left:40px\">\n" +
                "\t\t<pre id=\"final-output\"></pre>\n" +
                "\t</div>\n" +
                "</body>" +
                "</html>";
    }
}
