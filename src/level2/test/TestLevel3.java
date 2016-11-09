package level2.test;

import level2.command.CommandPerform;
import level2.constants.InstructionEnum;
import level2.interpreter.Bfck;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;

import static level2.constants.Sizes.MASK;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the slices of level 2
 */
public class TestLevel3 {
    private CommandPerform perf;
    private Bfck bfck;
    private ByteArrayOutputStream baos;

    @org.junit.Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none(); //Cutom library 'system rule'
    public final ExpectedException exception = ExpectedException.none();

    @org.junit.Test
    public void tests14_1() {
        String[] args = {"-p", "TestIndent"}; // short syntax
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(6 - MASK.get(), bfck.getMemoryAt((short) 0)); // checks if memory matches expected output
        assertEquals(2 - MASK.get(), bfck.getMemoryAt((short) 1));
    }

    @org.junit.Test
    public void tests14_2() {
        String[] args = {"-p", "TestComment"}; // short syntax
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(2 - MASK.get(), bfck.getMemoryAt((short) 0)); // checks if memory matches expected output
        assertEquals(2 - MASK.get(), bfck.getMemoryAt((short) 1));
    }
}
