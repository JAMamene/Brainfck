package level2.writer;

import level2.constants.Languages;
import level2.constants.Visualisable;

import java.util.List;

public class PythonWriter extends CodeWriter {

    @Override
    public void WriteFile(List<Visualisable> instructions, String fileName) {
        writeCode(instructions, "\t", fileName, ".py", Languages.python);
    }

    @Override
    protected String getHeader(String fileName) {
        return "import sys\n" +
                "import getch\n" +
                "def main():\n" +
                "\tsize = 30000\n" +
                "\tmem = [0] * size\n" +
                "\ti = 0\n";
    }

    @Override
    protected String getFooter() {
        return "\tfor i in range(0, size):\n" +
                "\t\tif mem[i] != 0:\n" +
                "\t\t\tsys.stdout.write(mem[i])\n";
    }
}
