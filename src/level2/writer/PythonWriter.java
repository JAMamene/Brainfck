package level2.writer;

import level2.constants.Languages;
import level2.constants.Visualisable;

import java.util.List;

public class PythonWriter extends CodeWriter {

    @Override
    public void WriteFile(List<Visualisable> instructions, String fileName) {
        writeCode(instructions, "", fileName, ".py", Languages.python);
    }

    @Override
    protected String getHeader(String fileName) {
        return "import sys\n" +
                "# Careful, same as C, if you press enter it will add it as an input\n" +
                "size = 30000\n" +
                "mem = [0] * size\n" +
                "i = 0\n";
    }

    @Override
    protected String getFooter() {
        return "for i in range(0, size):\n" +
                "\tif mem[i] != 0:\n" +
                "\t\tprint mem[i]\n";
    }
}
