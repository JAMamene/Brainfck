package level2.test;

import level2.command.CommandPerform;
import level2.interpreter.Bfck;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static level2.constants.Sizes.MASK;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the level 1 slices
 */
public class TestLevel1 {
    private CommandPerform perf;
    private Bfck bfck;

    @org.junit.Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none(); //Cutom library 'system rule'

    // test for slice 0

    @org.junit.Test
    public void tests0_1() {
        String[] args = {"-p", "TestEmpty"}; // Empty test, memory should be empty
        perf = new CommandPerform(args);
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 3)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 4)); // assert if the memory state is as expected
    }

    // test for slice 1

    @org.junit.Test
    public void tests1_1() {
        String[] args = {"-p", "TestINCR"}; // Only 4 incrementations for the first cell
        perf = new CommandPerform(args);
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(4 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1)); // assert if the memory state is as expected
    }

    // test for slice 2

    @org.junit.Test
    public void tests2_1() {
        String[] args = {"-p", "TestDECR"}; // Still 4 increments but also two decrements
        perf = new CommandPerform(args);
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(2 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1)); // assert if the memory state is as expected
    }

    // test for slice 3

    @org.junit.Test
    public void tests3_1() {
        String[] args = {"-p", "TestRIGHT"}; // Some RIGHT instructions in there
        perf = new CommandPerform(args);
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(2 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(2 - MASK.get(), bfck.getMemoryAt((short) 1)); // assert if the memory state is as expected
        assertEquals(1 - MASK.get(), bfck.getMemoryAt((short) 2)); // assert if the memory state is as expected
    }

    // test for slice 4

    @org.junit.Test
    public void tests4_1() {
        String[] args = {"-p", "TestLEFT"}; // Every instructions so far
        perf = new CommandPerform(args);
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(1 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(1 - MASK.get(), bfck.getMemoryAt((short) 1)); // assert if the memory state is as expected
        assertEquals(1 - MASK.get(), bfck.getMemoryAt((short) 2)); // assert if the memory state is as expected
    }
}
