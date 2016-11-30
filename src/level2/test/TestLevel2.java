package level2.test;

import level2.command.CommandPerform;
import level2.exceptions.SyntaxException;
import level2.interpreter.Bfck;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static level2.constants.Sizes.MASK;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the slices of level 2
 */
public class TestLevel2 {
    @org.junit.Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none(); //Cutom library 'system rule'
    public final ExpectedException exception = ExpectedException.none();
    private CommandPerform perf;
    private Bfck bfck;
    private ByteArrayOutputStream baos;

    @org.junit.Test
    public void tests5_1() {
        String[] args = {"-p", "Test1"}; // short syntax
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(8 - MASK.get(), bfck.getMemoryAt((short) 0)); // checks if memory matches expected output
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2));
        assertEquals(2 - MASK.get(), bfck.getMemoryAt((short) 3));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 4));
    }

    @org.junit.Test
    public void tests5_2() {
        String[] args = {"-p", "Test1,5"}; // same as above but with mixed syntax
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(8 - MASK.get(), bfck.getMemoryAt((short) 0)); // checks if memory matches expected output
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2));
        assertEquals(2 - MASK.get(), bfck.getMemoryAt((short) 3));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 4));
    }

    // Tests for slice 6

    // Tests for the rewrite action

    @org.junit.Test
    public void tests6_1() {
        exit.expectSystemExit();
        baos = new ByteArrayOutputStream(); // will redirect output to string
        System.setOut(new PrintStream(baos));
        String[] args = {"-p", "Test1", "--rewrite"}; // rewrite code
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAction(); // no need for other commands
        assertEquals("++++++++>>>++<++--\r\n", baos.toString()); // assert code is the same in short syntax
    }

    @org.junit.Test
    public void tests6_2() {
        exit.expectSystemExit();
        baos = new ByteArrayOutputStream(); // will redirect output to string
        System.setOut(new PrintStream(baos));
        String[] args = {"-p", "Test1,5", "--rewrite"}; // same as above but with mixed syntax
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAction(); // no need for other commands
        assertEquals("++++++++>>>++<++--\r\n", baos.toString()); // assert code is the same in short syntax
    }

    // test for slice 7

    @org.junit.Test
    public void tests7_1() {
        exit.expectSystemExit();
        baos = new ByteArrayOutputStream(); // Very simple image
        System.setOut(new PrintStream(baos));
        String[] args = {"-p", "Test1.bmp", "--rewrite"};
        perf = new CommandPerform(args);
        perf.performAction(); // no need for other commands
        assertEquals("+++\r\n", baos.toString());  // The image in short syntax should be like this
    }

    @org.junit.Test
    public void tests7_2() {
        exit.expectSystemExit();
        baos = new ByteArrayOutputStream(); // More complicated image
        System.setOut(new PrintStream(baos));
        String[] args = {"-p", "Test1,5.bmp", "--rewrite"};
        perf = new CommandPerform(args);
        perf.performAction(); // no need for other commands
        assertEquals("++++++++>>>++<++--\r\n", baos.toString()); // The image in short syntax should be like this
    }

    // Tests for slice 8

    @org.junit.Test
    public void tests8_1() {
        exit.expectSystemExit();
        String[] args = {"-p", "Test1,5", "--translate"}; // use translate option to create bmp from Test1,5
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAction(); // no need for other commands
        baos = new ByteArrayOutputStream(); // Now we use the image created to check if it is as expected (test7_2)
        System.setOut(new PrintStream(baos));
        String[] args2 = {"-p", "Test1,5.bmp", "--rewrite"};
        perf = new CommandPerform(args2);
        perf.performAction(); // no need for other commands
        assertEquals("++++++++>>>++<++--\r\n", baos.toString()); // The image in short syntax should be like this
    }

    // Tests for slice 9

    @org.junit.Test
    public void tests9_1() {
        String[] args = {"-p", "Test2"}; // mixed syntax with in and out in short syntax
        String str = "5";  // Set input stream to simulate the in instruction
        perf = new CommandPerform(args); //will perform the actions needed
        bfck = perf.getBfck();
        bfck.setIn(str);
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(55 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(6 - MASK.get(), bfck.getMemoryAt((short) 2));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 3));
    }

    @org.junit.Test
    public void tests9_2() {
        String[] args = {"-p", "Test2,5"}; // mixed syntax with in and out in long syntax
        String str = "5";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes()); // Set input stream to simulate the in instruction
        perf = new CommandPerform(args); //will perform the actions needed
        bfck = perf.getBfck();
        bfck.setIn(str);
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(55 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(6 - MASK.get(), bfck.getMemoryAt((short) 2));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 3));
    }

    @org.junit.Test
    public void tests9_3() {
        String[] args = {"-p", "Test2", "-i", "ITestInput"}; // ITestInput contains 34;
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(102 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(6 - MASK.get(), bfck.getMemoryAt((short) 2));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 3));
    }

    @org.junit.Test
    public void tests9_4() {
        exit.expectSystemExit();
        baos = new ByteArrayOutputStream(); // A rewrite test with all instructions
        System.setOut(new PrintStream(baos));
        String[] args = {"-p", "Test2", "--rewrite"};
        perf = new CommandPerform(args);
        perf.performAction(); // no need for other commands
        assertEquals(">>++++++++--<,.++\r\n", baos.toString());
    }

    @org.junit.Test
    public void tests9_5() {
        String[] args = {"-p", "TestMultiInput", "-i", "ITestMultiInput"}; // ITestInput contains 34;
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(97 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(98 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(99 - MASK.get(), bfck.getMemoryAt((short) 2));
        assertEquals(100 - MASK.get(), bfck.getMemoryAt((short) 3));
        assertEquals(101 - MASK.get(), bfck.getMemoryAt((short) 4));
    }

    // Tests for slice 10

    @org.junit.Test
    public void tests10_1() {
        String[] args = {"-p", "TestCheck1", "--check"};
        exit.expectSystemExitWithStatus(0); // should work
        perf = new CommandPerform(args); // should terminate the program with exit code 4
        perf.performAction();
    }

    @org.junit.Test
    public void tests10_2() {
        String[] args = {"-p", "TestCheck2", "--check"}; //too many closing brackets
        exit.expectSystemExitWithStatus(4); // check should raise exception
        exception.expect(SyntaxException.class);
        perf = new CommandPerform(args); // should terminate the program with exit code 4
        perf.performAction();
    }

    @org.junit.Test
    public void tests10_3() {
        String[] args = {"-p", "TestCheck3", "--check"}; //closing bracket at the beginning
        exception.expect(SyntaxException.class);
        exit.expectSystemExitWithStatus(4); // check should raise exception
        perf = new CommandPerform(args); // should terminate the program with exit code 4
        perf.performAction();
    }

    @org.junit.Test
    public void tests10_4() {
        String[] args = {"-p", "TestCheck1", "--check", "--translate", "--rewrite"}; //closing bracket at the beginning
        exception.expect(SyntaxException.class);
        exit.expectSystemExitWithStatus(0); // check should raise exception
        perf = new CommandPerform(args); // should terminate the program with exit code 4
        perf.performAction();
    }

    @org.junit.Test
    public void tests11_1() {
        String[] args = {"-p", "TestAddition"}; //3 + 4
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(7 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1));
    }

    @org.junit.Test
    public void tests11_2() {
        String[] args = {"-p", "TestMultiplication"}; //8 * 7
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
        bfck = perf.getBfck();
        assertEquals(56 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1));
    }
}
