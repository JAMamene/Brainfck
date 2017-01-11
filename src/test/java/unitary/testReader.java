package unitary;

import level4.instructions.Executable;
import level4.instructions.InstructionEnum;
import level4.reader.BfReader;
import level4.reader.ModulableReader;
import level4.utils.Function;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static level4.instructions.InstructionEnum.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testReader {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private List<Executable> instructions;

    private void createAndFillFile(String content) {
        try {
            File createdFile = folder.newFile("tmp");
            BufferedWriter bw = new BufferedWriter(new FileWriter(createdFile));
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Executable> testInstructionReader(String context) {
        createAndFillFile(context);
        BfReader reader = new ModulableReader();
        return reader.readFile(folder.getRoot().toString() + "\\tmp");
    }


    @org.junit.Test
    public void testReadEmptyFile() {
        instructions = testInstructionReader("");
        assertTrue(instructions.isEmpty()); // assert if the instructions are as expected
    }

    @org.junit.Test
    public void testReadINCR() {
        instructions = testInstructionReader("+");
        assertEquals(Collections.singletonList(INCR), instructions); // assert if the instructions are as expected
    }

    @org.junit.Test
    public void testReadMultiple() {
        instructions = testInstructionReader("INCR\nDECR");
        assertEquals(Arrays.asList(INCR, DECR), instructions); // assert if the instructions are as expected
    }

    @org.junit.Test
    public void testReadAllShort() {
        instructions = testInstructionReader("+-<>.,[]");
        assertEquals(Arrays.asList(INCR, DECR, LEFT, RIGHT, OUT, IN, JUMP, BACK), instructions); // assert if the instructions are as expected
    }

    @org.junit.Test
    public void testShortAndLong() {
        instructions = testInstructionReader("INCR\n-<>\nOUT\nIN\n[]");
        assertEquals(Arrays.asList(INCR, DECR, LEFT, RIGHT, OUT, IN, JUMP, BACK), instructions); // assert if the instructions are as expected
    }

    @org.junit.Test
    public void testIndent() {
        instructions = testInstructionReader("INCR\n\tDECR\n\tINCR");
        assertEquals(Arrays.asList(INCR, DECR, INCR), instructions); // assert if the instructions are as expected
    }

    @org.junit.Test
    public void testComment() {
        instructions = testInstructionReader("" +
                "INCR\n" +
                "   DECR # This is a comment\n" +
                "   INCR# This is another comment ++INCR\n");
        assertEquals(Arrays.asList(INCR, DECR, INCR), instructions); // assert if the instructions are as expected
    }

    @org.junit.Test
    public void testComment2() {
        instructions = testInstructionReader("" +
                "INCR\n" +
                "   DECR ## This is a comment INCR ##\n");
        assertEquals(Arrays.asList(INCR, DECR), instructions); // assert if the instructions are as expected
    }

    @org.junit.Test
    public void testMacro() {
        instructions = testInstructionReader("" +
                "{ MULTI_INCR \n" +
                "   INCR # This is a comment\n" +
                "   INCR # This is a comment\n" +
                "   INCR # This is a comment\n" +
                "}\n" +
                "MULTI_INCR");
        assertEquals(Arrays.asList(INCR, INCR, INCR), instructions); // assert if the instructions are as expected
    }

    @org.junit.Test
    public void testMacroParameter() {
        instructions = testInstructionReader("" +
                "{ MULTI_INCR \n" +
                "   INCR # This is a comment\n" +
                "}\n" +
                "MULTI_INCR%10 #COMMMENT\n");
        assertEquals(Arrays.asList(INCR, INCR, INCR, INCR, INCR, INCR, INCR, INCR, INCR, INCR), instructions); // assert if the instructions are as expected
    }

    @org.junit.Test
    public void testMacroShort() {
        instructions = testInstructionReader("" +
                "{ MULTI_INCR \n" +
                "   + # This is a comment\n" +
                "}\n" +
                "MULTI_INCR%10 #COMMMENT\n");
        assertEquals(Arrays.asList(INCR, INCR, INCR, INCR, INCR, INCR, INCR, INCR, INCR, INCR), instructions); // assert if the instructions are as expected
    }

    @org.junit.Test
    public void testEquivalent() {
        instructions = testInstructionReader("+++--<++");
        List<Executable> instructions2 = testInstructionReader("" +
                "{ MULTI_INCR \n" +
                "   + # This is a comment\n" +
                "}\n" +
                "MULTI_INCR%3 #COMMMENT\n" +
                "DECR\n" +
                "--<#Comment\n" +
                "+\n" +
                "INCR\r");
        assertEquals(instructions2, instructions); // assert if the instructions are as expected
    }
    @Test
    public void testFunction(){
        List<Executable> instruction = new ArrayList<>();
        instruction.add(InstructionEnum.INCR);
        instruction.add(InstructionEnum.DECR);
        instruction.add(InstructionEnum.INCR);
        instruction.add(InstructionEnum.DECR);
        instruction.add(InstructionEnum.INCR);
        instruction.add(InstructionEnum.INCR);
        instruction.add(InstructionEnum.DECR);
        instruction.add(InstructionEnum.DECR);
        Function test = new Function(instruction);
        List<Executable> instructions = testInstructionReader("(Func salut\n" +
                "INCR\n" +
                "DECR\n" +
                "INCR\n" +
                "DECR\n" +
                "++\n" +
                "--\n" +
                ")\n" +
                "salut[]");
        assertEquals(test,instructions.get(0));
    }

    @Test
    public void testMultiFunc(){
        List<Executable> instructions = testInstructionReader("(Func Test\n" +
                "INCR\n" +
                "INCR\n" +
                "JUMP\n" +
                "BACK\n" +
                ")\n" +
                "\n" +
                "(Func salut\n" +
                "INCR\n" +
                "DECR\n" +
                "INCR\n" +
                "DECR\n" +
                "++\n" +
                "--\n" +
                "Test[]\n" +
                ")\n" +
                "salut[]");
        List<Executable> test1 = new ArrayList<>();
        test1.add(InstructionEnum.INCR);
        test1.add(InstructionEnum.INCR);
        test1.add(InstructionEnum.JUMP);
        test1.add(InstructionEnum.BACK);
        Function Test1 = new Function(test1);
        List<Executable> instruction = new ArrayList<>();
        instruction.add(InstructionEnum.INCR);
        instruction.add(InstructionEnum.DECR);
        instruction.add(InstructionEnum.INCR);
        instruction.add(InstructionEnum.DECR);
        instruction.add(InstructionEnum.INCR);
        instruction.add(InstructionEnum.INCR);
        instruction.add(InstructionEnum.DECR);
        instruction.add(InstructionEnum.DECR);
        instruction.add(Test1);
        Function test = new Function(instruction);
        assertEquals(test,instructions.get(0));
    }
    @Test
    public void TestMacroFunction(){
        instructions = testInstructionReader("{ HELLO\n" +
                "INCR\n" +
                "DECR\n" +
                "INCR\n" +
                "DECR\n" +
                "}\n" +
                "\n" +
                "(Func salut\n" +
                "HELLO\n" +
                "++\n" +
                "--\n" +
                ")\n" +
                "salut[]");
        List<Executable> instruction = new ArrayList<>();
        instruction.add(InstructionEnum.INCR);
        instruction.add(InstructionEnum.DECR);
        instruction.add(InstructionEnum.INCR);
        instruction.add(InstructionEnum.DECR);
        instruction.add(InstructionEnum.INCR);
        instruction.add(InstructionEnum.INCR);
        instruction.add(InstructionEnum.DECR);
        instruction.add(InstructionEnum.DECR);
        Function test = new Function(instruction);
        assertEquals(test,instructions.get(0));
    }
    @Test
    public void declarationOrder(){
        instructions = testInstructionReader("salut[]\n" +
                "(Func salut\n" +
                "++\n" +
                ")");
        List<Executable> instru = Arrays.asList(INCR,INCR);
        Function test = new Function(instru);
        assertEquals(instructions.get(0),test);
    }
}
