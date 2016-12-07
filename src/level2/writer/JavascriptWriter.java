package level2.writer;

import level2.constants.Languages;
import level2.constants.Visualisable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JavascriptWriter extends CodeWriter {

    @Override
    public void WriteFile(List<Visualisable> instructions, String fileName) {
        BufferedWriter htmlw;
        File outputFile = new File(removeExtension(fileName) + ".html"); //File to call the js code from
        try {
            htmlw = new BufferedWriter(new FileWriter(outputFile));
            htmlw.write(getHTML(fileName));
            htmlw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeCode(instructions, "\t", fileName, ".js", Languages.js);
    }

    @Override
    protected String getHeader(String fileName) {
        return "function Bfck() {\n" +
                "\tmem = Array.apply(null, Array(30000)).map(Number.prototype.valueOf,0);\n" +
                "\tvar i = 0;";
    }

    @Override
    protected String getFooter() {
        return "\tfor (var j = 0; j < mem.length; j++) {\n" +
                "\t\tif (mem[j] != 0) {\n" +
                "\t\t\tdocument.getElementById(\"final-output\").innerHTML += \"<br/>\".concat(\"[\" + j + \"] = \" + mem[j]);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";
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
                "</body>\n" +
                "</html>";
    }
}
