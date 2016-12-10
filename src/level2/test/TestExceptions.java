package level2.test;

import level2.command.CommandPerform;
import level2.exceptions.ExecuteException;
import level2.exceptions.FileException;
import level2.exceptions.SyntaxException;
import level2.interpreter.Bfck;
import level2.interpreter.Memory;
import org.junit.Before;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;

/**
 * Tests for the different exceptions and exits our program can face
 */
public class TestExceptions {
    @org.junit.Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none(); //Cutom library 'system rule'
    public final ExpectedException exception = ExpectedException.none();
    private CommandPerform perf;
    private Memory bfck;

    // test for the WrongFile exceptions

    @org.junit.Test
    public void testWrongProp() {
        String[] args = {"-p", "WrongProp.bmp"};
        exception.expect(FileException.class);
        exit.expectSystemExitWithStatus(3); // should terminate the program with exit code 3
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
    }

    @org.junit.Test
    public void testWrongImage() {
        String[] args = {"-p", "WrongColor.bmp"};
        exception.expect(SyntaxException.class);
        exit.expectSystemExitWithStatus(4); // should terminate the program with exit code 4
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
    }

    // Tests for the syntaxException

    @org.junit.Test
    public void testInvalidOutput() {
        String[] args = {"-p", "TestUnknownChar"}; // random char in bf file
        exception.expect(SyntaxException.class);
        exit.expectSystemExitWithStatus(4); // should terminate the program with exit code 4
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
    }

    // Tests for the argument handling

    @org.junit.Test
    public void testArgs1() {
        String[] args = {"-p", "TestCheck3", "--check", "random"};
        exit.expectSystemExitWithStatus(3); // random argument should raise exception
        perf = new CommandPerform(args);
        perf.performAll();
    }

    @org.junit.Test
    public void testArgs2() {
        String[] args = {"-p", "TestCheck3", "--check", "-o"};
        exit.expectSystemExitWithStatus(3); // no output file specified should broke
        perf = new CommandPerform(args);
        perf.performAll();
    }

    @org.junit.Test
    public void testArgs3() {
        String[] args = {};
        exit.expectSystemExitWithStatus(3); // no arguments should lead to exit 3
        perf = new CommandPerform(args);
        perf.performAll();
    }

    @org.junit.Test
    public void testArgs4() {
        String[] args = {"-p", "Unexisting_file"};
        exit.expectSystemExitWithStatus(3); // non existent file should lead to exit 3
        perf = new CommandPerform(args);
        perf.performAll();
    }

    // Tests for the execution exceptions

    @org.junit.Test
    public void testCellOverflow() {
        String[] args = {"-p", "TestCellOverflow"}; // value greater than 255 in cell
        perf = new CommandPerform(args); //will perform the actions needed
        exception.expect(ExecuteException.class); // should terminate the program with exit code 1
        exit.expectSystemExitWithStatus(1);
        perf.performAll();
    }

    @org.junit.Test
    public void testCellUnderflow() {
        String[] args = {"-p", "TestCellUnderflow"}; // negative value in cell
        perf = new CommandPerform(args);
        exception.expect(ExecuteException.class);
        exit.expectSystemExitWithStatus(1); // should terminate the program with exit code 1
        perf.performAll();
    }

    @org.junit.Test
    public void testMemoryOverflow() {
        String[] args = {"-p", "TestMemoryOverflow"}; // pointer greater than 30000
        perf = new CommandPerform(args);
        exception.expect(ExecuteException.class);
        exit.expectSystemExitWithStatus(2); // should terminate the program with exit code 2
        perf.performAll();
    }

    @org.junit.Test
    public void testMemoryUnderflow() {
        String[] args = {"-p", "TestMemoryUnderflow"}; // negative pointer
        perf = new CommandPerform(args); //will perform the actions needed
        exception.expect(ExecuteException.class);
        exit.expectSystemExitWithStatus(2); // should terminate the program with exit code 2
        perf.performAll();
    }

    @org.junit.Test
    public void testInvalidInput1() {
        exception.expect(ExecuteException.class);
        exit.expectSystemExitWithStatus(1); // should terminate the program with exit code 1
        String[] args = {"-p", "Test2"};
        String str = "ҕ"; // input to big
        perf = new CommandPerform(args); //will perform the actions needed
        bfck = perf.getContainer().getBfck();
        perf.getContainer().setIn(str);
        perf.performAll();
        perf.performAll();
    }

    @org.junit.Test
    public void testInvalidInput2() {
        exception.expect(FileException.class);
        exit.expectSystemExitWithStatus(3); // should terminate the program with exit code 3
        String[] args = {"-p", "Test2"};
        String str = ""; // input invalid
        perf = new CommandPerform(args); //will perform the actions needed
        bfck = perf.getContainer().getBfck();
        perf.getContainer().setIn(str);
        perf.performAll();
        perf.performAll();
    }

    @org.junit.Test
    public void testsNotEnoughChar() {
        String[] args = {"-p", "TestMultiInput", "-i", "ITestInput"}; //Doesn't contain enough char
        perf = new CommandPerform(args); //will perform the actions needed
        exception.expect(FileException.class); //Should throw wrongfile exception
        exit.expectSystemExitWithStatus(3); // should terminate the program with exit code 3
        perf.performAll();
    }

    @org.junit.Test
    public void testInvalidMacro() {
        String[] args = {"-p", "TestMacroError"}; // Wrong syntax for macro
        exception.expect(SyntaxException.class); //Should throw wrongfile exception
        exit.expectSystemExitWithStatus(4); // should terminate the program with exit code 4
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
    }

    @org.junit.Test
    public void testInvalidMacroArg() {
        String[] args = {"-p", "TestMacroInvalidArg"}; // Wrong syntax for macro
        exception.expect(SyntaxException.class); //Should throw wrongfile exception
        exit.expectSystemExitWithStatus(4); // should terminate the program with exit code 4
        perf = new CommandPerform(args); //will perform the actions needed
        perf.performAll();
    }

}
