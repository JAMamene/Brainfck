package level2.test.unitary;

import level2.command.SetInCommand;
import level2.constants.Executable;
import level2.exceptions.ExecuteException;
import level2.interpreter.Bfck;
import level2.interpreter.BfckContainer;
import level2.interpreter.Memory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static level2.constants.InstructionEnum.*;
import static level2.constants.Sizes.MASK;
import static org.junit.Assert.assertEquals;

/**
 * Tests the bfck class and the instructions (no test for the enum because it depends heavily on bfck)
 */
public class testBfck {

    public final ExpectedException exception = ExpectedException.none();
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none(); //Cutom library 'system rule'
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private List<Executable> instructions;
    private BfckContainer container;
    private Memory bfck;

    private void testInstruction(List<Executable> instructions) {
        container = new BfckContainer(instructions, null, null, null);
        container.handle();
        bfck = container.getBfck();
    }

    @Test
    public void testEmpty() {
        instructions = new ArrayList<>(); //test with an empty set of instructions
        testInstruction(instructions);
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 3));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 4));
    }

    @Test
    public void testINCR1() {
        instructions = Collections.singletonList(INCR); //Test incr
        testInstruction(instructions);
        assertEquals(bfck.getPointer(), 0);
        assertEquals(1 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2));
    }

    @Test
    public void testINCR2() {
        instructions = Arrays.asList(INCR, INCR, INCR, INCR); // 4 INCR
        testInstruction(instructions);
        assertEquals(bfck.getPointer(), 0);
        assertEquals(4 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2));
    }

    @Test
    public void testINCROverflow() {
        instructions = new ArrayList<>();
        for (int i = 0; i <= 255; i++) {
            instructions.add(INCR);
        }
        exception.expect(ExecuteException.class); // should terminate the program with exit code 1
        exit.expectSystemExitWithStatus(1);
        testInstruction(instructions);
    }

    @Test
    public void testDECR1() {
        instructions = Arrays.asList(INCR, INCR, DECR); //test with one decr
        testInstruction(instructions);
        assertEquals(bfck.getPointer(), 0);
        assertEquals(1 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2));
    }

    @Test
    public void testDECR2() {
        instructions = Arrays.asList(INCR, INCR, INCR, DECR, DECR); //test with two decr
        testInstruction(instructions);
        assertEquals(bfck.getPointer(), 0);
        assertEquals(1 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2));
    }

    @Test
    public void testDECRUnderflow() {
        instructions = Collections.singletonList(DECR); //test with one DECR
        exception.expect(ExecuteException.class); // should terminate the program with exit code 1
        exit.expectSystemExitWithStatus(1);
        testInstruction(instructions);
    }


    @Test
    public void testRIGHT1() {
        instructions = Arrays.asList(RIGHT, INCR); //test with one RIGHT
        testInstruction(instructions);
        assertEquals(bfck.getPointer(), 1);
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(1 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2));
    }

    @Test
    public void testRIGHT2() {
        instructions = Arrays.asList(RIGHT, RIGHT, RIGHT, INCR); //test with two decr
        testInstruction(instructions);
        assertEquals(bfck.getPointer(), 3);
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2));
        assertEquals(1 - MASK.get(), bfck.getMemoryAt((short) 3));
    }

    @Test
    public void testRIGHTOverflow() {
        instructions = new ArrayList<>();
        for (int i = 0; i <= 30000; i++) { //Go out of the memory
            instructions.add(RIGHT);
        }
        exception.expect(ExecuteException.class); // should terminate the program with exit code 2
        exit.expectSystemExitWithStatus(2);
        testInstruction(instructions);
    }

    @Test
    public void testLEFT1() {
        instructions = Arrays.asList(RIGHT, LEFT, INCR); //test with one RIGHT AND LEFT
        testInstruction(instructions);
        assertEquals(bfck.getPointer(), 0);
        assertEquals(1 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2));
    }

    @Test
    public void tetsLEFT2() {
        instructions = Arrays.asList(RIGHT, RIGHT, RIGHT, RIGHT, LEFT, LEFT, LEFT, INCR); //Move left and right
        testInstruction(instructions);
        assertEquals(bfck.getPointer(), 1);
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(1 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 3));
    }

    @Test
    public void testLEFTUnderflow() {
        instructions = Collections.singletonList(LEFT); // Go out of the memory with one left
        exception.expect(ExecuteException.class); // should terminate the program with exit code 2
        exit.expectSystemExitWithStatus(2);
        testInstruction(instructions);
    }

    @Test
    public void testOUT1() {
        instructions = Arrays.asList(INCR, OUT); //Should print the character 1 (ascii)
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // will redirect output to string
        System.setOut(new PrintStream(baos));
        testInstruction(instructions);
        Character i = 1;
        assertEquals(i.toString(), baos.toString());
    }

    @Test
    public void testOUT2() {
        instructions = Arrays.asList(INCR, OUT, RIGHT, INCR, INCR, OUT); // should print 12
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // will redirect output to string
        System.setOut(new PrintStream(baos));
        testInstruction(instructions);
        Character i = 1; // Memory state is [1,2,0,0...]
        Character j = 2;
        assertEquals(i.toString() + j.toString(), baos.toString());
    }

    @Test
    public void testIN1() {
        instructions = Arrays.asList(RIGHT, IN); // test in
        container = new BfckContainer(instructions,null,null,null);
        String str = "1";
        container.setIn(str);
        container.handle();
        bfck = container.getBfck();
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(49 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 3));
    }

    @Test
    public void testIN2() {
        instructions = Arrays.asList(IN, RIGHT, IN); // two input from file
        String content = "1\n2"; // the 2 values of in
        try {
            File createdFile = folder.newFile("ab"); // try to put it in a file and use it as entry
            BufferedWriter bw = new BufferedWriter(new FileWriter(createdFile));
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SetInCommand in = new SetInCommand();
        container = new BfckContainer(instructions, null, folder.getRoot().toString() + "\\ab", null);
        in.execute(container);
        container.handle();
         bfck = container.getBfck();
        assertEquals(49 - MASK.get(), bfck.getMemoryAt((short) 0)); // assert if the memory state is as expected
        assertEquals(50 - MASK.get(), bfck.getMemoryAt((short) 1));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 2));
        assertEquals(0 - MASK.get(), bfck.getMemoryAt((short) 3));
    }

}
