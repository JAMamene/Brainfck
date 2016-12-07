package level2.constants;

import level2.writer.*;

import java.util.Arrays;

/**
 * enum for the supported language to recode brainfuck with
 */
public enum Languages {
    java(new JavaWriter()),
    c(new CWriter()),
    js(new JavascriptWriter()),
    python(new PythonWriter());

    private BfWriter bfw;

    Languages(BfWriter bfw) {
        this.bfw = bfw;
    }

    public BfWriter getCodeClass() {
        return this.bfw;
    }
}
