package level2.test;

import level2.command.CommandPerform;
import level2.constants.Metrics;
import level2.interpreter.Bfck;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static level2.constants.Sizes.MASK;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the slices of level 2
 */
public class TestLevel3 {
    private CommandPerform perf;
    private Bfck bfck;

    @org.junit.Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none(); //Cutom library 'system rule'
    public final ExpectedException exception = ExpectedException.none();

    @org.junit.Test
    public void tests12_1() {
        Metrics.reset(); //reset metrics just to be sure
        String[] args = {"-p", "Test1"}; //test metrics without loops
        perf = new CommandPerform((args));
        perf.performAll();
        assertEquals(18, Metrics.getProgSize());
        assertEquals(18, Metrics.getExecMove());
        assertEquals(4, Metrics.getDataMove());
        assertEquals(14, Metrics.getDataWrite());
        assertEquals(0, Metrics.getDataRead());
    }

    @org.junit.Test
    public void tests12_2() {
        Metrics.reset();
        String[] args = {"-p", "TestJB"}; //test metrics with loops
        perf = new CommandPerform((args));
        perf.performAll();
        assertEquals(18, Metrics.getProgSize());
        assertEquals(39, Metrics.getExecMove());
        assertEquals(9, Metrics.getDataMove());
        assertEquals(17, Metrics.getDataWrite());
        assertEquals(13, Metrics.getDataRead());
    }

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

    @org.junit.Test
    public void tests15_1() {
        String[] args = {"-p", "TestMacro"}; // triple decr
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(3 - MASK.get(), bfck.getMemoryAt((short) 0)); // checks if memory matches expected output
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1));
    }

    @org.junit.Test
    public void tests15_2() {
        String[] args = {"-p", "TestEmptyMacro"}; // empty macro, should work anyway
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(2 - MASK.get(), bfck.getMemoryAt((short) 0)); // checks if memory matches expected output
        assertEquals(1 - MASK.get(), bfck.getMemoryAt((short) 1));
    }

    @org.junit.Test
    public void tests16_1() {
        String[] args = {"-p", "TestMacro2"}; // parametrized macro
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 0)); // checks if memory matches expected output
        assertEquals(10 - MASK.get(), bfck.getMemoryAt((short) 1));
    }

    @org.junit.Test
    public void tests16_2() {
        String[] args = {"-p", "TestMacroRec"}; // parametrized macro (with comment and spaces !)
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 0)); // checks if memory matches expected output
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(3 - MASK.get(), bfck.getMemoryAt((short) 2));
    }

    @org.junit.Test
    public void tests16_3() {
        exit.expectSystemExit();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] args = {"-p", "TestMacroRec", "--rewrite"}; // parametrized macro
        perf = new CommandPerform(args); //will perform the actions needed
        System.setOut(new PrintStream(baos));
        perf.performAction(); // no need for other commands
        assertEquals(">>+++\r\n", baos.toString()); // is translated at execution
    }
}
